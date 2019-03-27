package com.chatclient.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;

import com.zzchat.model.Message;
import com.zzchat.model.MessageType;
import com.zzchatclient.control.ClientConnect;

public class FriendChat extends JFrame implements ActionListener,Runnable{

	
	//
	JScrollPane jsp;
	JTextArea jta;

	
	//
	JPanel jp;
	JTextField jtf;
	JButton jb;
	
	String sender;
	String receiver;
	
	public FriendChat(String sender, String receiver){
		
		this.sender=sender;
		this.receiver=receiver;
		
		jta = new JTextArea();
		jta.setEditable(false);
		jsp =new JScrollPane(jta);
		this.add(jsp,"Center");
		
		
		jp =new JPanel();
		jtf =new JTextField(15);
		jtf.setForeground(Color.red);
		jb=new JButton("发送");
		jb.addActionListener(this);
		jp.add(jtf);
		jp.add(jb);
		this.add(jp,"South");
		
		
		this.setSize(350,240);
		this.setTitle(sender+"正在和"+receiver+"聊天");
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	
	}
	public static void main(String[] args) {
		//FriendChatClient FriendChatClient=new FriendChatClient();


	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource()==jb) jta.append(jtf.getText()+"\r\n");
		
		
		//向服务器发送聊天信息
		Message mess=new Message();
		mess.setSender(sender);
		mess.setReceiver(receiver);
		mess.setContent(jtf.getText());
		mess.setMessageType(Message.message_Common);
		ObjectOutputStream oos;
		try{
		oos=new ObjectOutputStream(ClientConnect.s.getOutputStream());
		oos.writeObject(mess);
		} catch (IOException e){
			e.printStackTrace();
		}
		}
	@Override
	public void run() {
		ObjectInputStream ois;
		while(true){
			try{
				ois=new ObjectInputStream(ClientConnect.s.getInputStream());
				Message mess=(Message)ois.readObject();//接受聊天信息
				String showMessage=mess.getSender()+"对"+mess.getReceiver()+"说"+mess.getContent();
				System.out.println(showMessage);
				jta.append(showMessage+"\r\n");
			}catch (IOException | ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
	}	

}

