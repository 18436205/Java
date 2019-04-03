package com.zzchatclient.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

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
				Message mess=(Message)ois.readObject();//����������Ϣ
				String showMessage=mess.getSender()+"��"+mess.getReceiver()+"˵:\r\n                                                        "+mess.getContent();
				System.out.println(showMessage);
				//jta.append(showMessage+"\r\n");
				
				FriendChat1 friendChat1=(FriendChat1)FriendList.hmFriendChat1.get(mess.getReceiver()+"to"+mess.getSender());
				
				friendChat1.appendJta(showMessage);
			}catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		

	}

}