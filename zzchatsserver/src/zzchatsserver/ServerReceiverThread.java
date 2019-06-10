package zzchatsserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

import com.zzchat.model.Message;
import com.zzchat.model.MessageType;

public class ServerReceiverThread extends Thread{
	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Message mess;
	String  sender;
	public ServerReceiverThread(Socket s) {
	this.s=s;
	}
		
	public void run() {
		
		while(true) {
		try {
			ois =new ObjectInputStream(s.getInputStream());
			mess=(Message)ois.readObject();
			sender=mess.getSender( );
			System.out.println(mess.getSender()+"对"+mess.getReceiver()+"说:"+mess.getContent());
		
			
			if(mess.getMessageType().equals(Message.message_ClearFriend)){
				String clearFriendName=mess.getContent();
				System.out.print("用户名："+sender + "需要删除的好友的名字："+clearFriendName);
				if(!ZzchatDbUtil.seeKUser(clearFriendName)){
					mess.setMessageType(Message.message_ClearFriendFailure_NoUser);
				}else{
					String relationType="1";//1表示好友
					if(ZzchatDbUtil.seekRelation(sender,clearFriendName,relationType)){
						int  count=ZzchatDbUtil.clearRelation(sender,clearFriendName,relationType);	
						if(count!=0){
							mess.setMessageType(Message.message_ClearFriendSuccess);
							//拿到全部好友
							String allFriedName=ZzchatDbUtil.getFriendString(sender);
							mess.setContent(allFriedName);
						}
					}
				}sendMessage(s, mess);
			}
			
			if(mess.getMessageType().equals(Message.message_AddFriend)){
				String addFriendName=mess.getContent();
				System.out.print("需要添加新好友的名字"+addFriendName);
				if(!ZzchatDbUtil.seeKUser(addFriendName)){
					mess.setMessageType(Message.message_AddFriendFailure_NoUser);
				}else{
					String relationType="1";//1表示好友
					if(ZzchatDbUtil.seekRelation(sender,addFriendName,relationType)){
						mess.setMessageType(Message.message_AddFriendFailure_AlreadFriend);
					}else{
						//添加
						int count=ZzchatDbUtil.addRelation(sender,addFriendName,relationType);
						if(count!=0){
							mess.setMessageType(Message.message_AddFriendSuccess);
							//拿到全部好友
							String allFriedName=ZzchatDbUtil.getFriendString(sender);
							mess.setContent(allFriedName);
						}
					}
				}
				sendMessage(s, mess);
			}
			
			
			
		if(mess.getMessageType().equals(Message.message_Common)){
			Socket s1=(Socket)StartServer.hmSocket.get(mess.getReceiver());
			sendMessage(s1,mess);
		}	
		//第二步
		if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
			Set friendSet=StartServer.hmSocket.keySet();//键值集合，在线好友集合
			Iterator it=friendSet.iterator();
			String friendName;
			String friendString=" ";
			while(it.hasNext()){//判断有没有下一个元素
				friendName=(String)it.next();//取出下一个元素
				if(!friendName.equals(mess.getSender()))
					friendString=friendName+" "+friendString+" ";
			}
			System.out.println("全部好友的名字"+friendString);
			
			
			//发送到客户端
			mess.setContent(friendString );
			mess.setMessageType(Message.message_OnlineFriend );
			mess.setSender("Server");
			mess.setReceiver(sender);
			sendMessage(s,mess);
		
			}
		
		
	}catch(IOException | ClassNotFoundException e){
		e.printStackTrace();
	}
	
	}
}

	private void sendMessage(Socket s, Message mess) throws IOException {
		// TODO Auto-generated method stub
		oos =new ObjectOutputStream(s.getOutputStream());
		oos.writeObject(mess);
		
	}
}
