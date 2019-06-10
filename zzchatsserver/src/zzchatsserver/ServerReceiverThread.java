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
			System.out.println(mess.getSender()+"��"+mess.getReceiver()+"˵:"+mess.getContent());
		
			
			if(mess.getMessageType().equals(Message.message_ClearFriend)){
				String clearFriendName=mess.getContent();
				System.out.print("�û�����"+sender + "��Ҫɾ���ĺ��ѵ����֣�"+clearFriendName);
				if(!ZzchatDbUtil.seeKUser(clearFriendName)){
					mess.setMessageType(Message.message_ClearFriendFailure_NoUser);
				}else{
					String relationType="1";//1��ʾ����
					if(ZzchatDbUtil.seekRelation(sender,clearFriendName,relationType)){
						int  count=ZzchatDbUtil.clearRelation(sender,clearFriendName,relationType);	
						if(count!=0){
							mess.setMessageType(Message.message_ClearFriendSuccess);
							//�õ�ȫ������
							String allFriedName=ZzchatDbUtil.getFriendString(sender);
							mess.setContent(allFriedName);
						}
					}
				}sendMessage(s, mess);
			}
			
			if(mess.getMessageType().equals(Message.message_AddFriend)){
				String addFriendName=mess.getContent();
				System.out.print("��Ҫ����º��ѵ�����"+addFriendName);
				if(!ZzchatDbUtil.seeKUser(addFriendName)){
					mess.setMessageType(Message.message_AddFriendFailure_NoUser);
				}else{
					String relationType="1";//1��ʾ����
					if(ZzchatDbUtil.seekRelation(sender,addFriendName,relationType)){
						mess.setMessageType(Message.message_AddFriendFailure_AlreadFriend);
					}else{
						//���
						int count=ZzchatDbUtil.addRelation(sender,addFriendName,relationType);
						if(count!=0){
							mess.setMessageType(Message.message_AddFriendSuccess);
							//�õ�ȫ������
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
		//�ڶ���
		if(mess.getMessageType().equals(Message.message_RequestOnlineFriend)){
			Set friendSet=StartServer.hmSocket.keySet();//��ֵ���ϣ����ߺ��Ѽ���
			Iterator it=friendSet.iterator();
			String friendName;
			String friendString=" ";
			while(it.hasNext()){//�ж���û����һ��Ԫ��
				friendName=(String)it.next();//ȡ����һ��Ԫ��
				if(!friendName.equals(mess.getSender()))
					friendString=friendName+" "+friendString+" ";
			}
			System.out.println("ȫ�����ѵ�����"+friendString);
			
			
			//���͵��ͻ���
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
