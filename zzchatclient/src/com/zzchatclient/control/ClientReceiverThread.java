package com.zzchatclient.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.chatclient.view.ClientLogin;
import com.chatclient.view.FriendChat1;
import com.chatclient.view.FriendList;
import com.zzchat.model.Message;

public class ClientReceiverThread extends Thread{

	private Socket s;
	public ClientReceiverThread(Socket s) {
		this.s=s;
	}
	
	public void run() {
		ObjectInputStream ois;
		while(true){
			try{
				ois=new ObjectInputStream(s.getInputStream());
				Message mess=(Message)ois.readObject();//接受聊天信息
				String showMessage=mess.getSender()+"对"+mess.getReceiver()+"说:\r\n"+mess.getContent();
				System.out.println(showMessage);
				//jta.append(showMessage+"\r\n");
				
				if(mess.getMessageType().equals(Message.message_AddFriendFailure_NoUser)){
					JOptionPane.showMessageDialog(null,"添加失败！用户不存在！");
				}
				
				if(mess.getMessageType().equals(Message.message_AddFriendFailure_AlreadFriend)){
					JOptionPane.showMessageDialog(null,"添加失败！该用户已是您好友！");
				}
				
				if(mess.getMessageType().equals(Message.message_AddFriendSuccess)){
					JOptionPane.showMessageDialog(null,"添加成功！");
					String allFriendName=mess.getContent();
					FriendList friendList=(FriendList)ClientLogin.hmFriendlist.get(mess.getSender());
					friendList.updateFriendIcon(allFriendName);
					friendList.revalidate();
				}
				
				if(mess.getMessageType().equals(Message.message_Common)){
				
				FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
				friendChat1.appendJta(showMessage);
				}
			
				
				if(mess.getMessageType().equals(Message.message_OnlineFriend)){
					System.out.println("在线好友"+mess.getContent());
					
					FriendList friendList=(FriendList)ClientLogin.hmFriendlist.get(mess.getReceiver());
					
					friendList.setEnableFriendIcon(mess.getContent());;
				}
				if(mess.getMessageType().equals(Message.message_NewOnlineFriend)){
					System.out.println("新用户上线，用户名："+mess.getContent());
					FriendList friendList=(FriendList)ClientLogin.hmFriendlist.get(mess.getReceiver());
					//验证对象是否 创建正确
					System.out.println("FriendList对象名"+mess.getReceiver());
					friendList.setEnableFriendIcon(mess.getContent());
				}
				
				
			}catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {
		

	}

}
