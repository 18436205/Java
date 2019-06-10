package com.chatclient.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.*;

import com.zzchat.model.Message;
import com.zzchatclient.control.ClientConnect;

public class FriendList extends JFrame implements ActionListener,MouseListener{//顶层容器
	
	public static HashMap hmFriendChat1=new HashMap<String,FriendChat1>();//键值对
	CardLayout cardLayout;//卡片布局
	
	JPanel myFriendPanel;
	JButton myFriendJButton;
	
	JScrollPane myFriendScrollPane,myStrangerScrollPane,BlackListScrollpane;
	JPanel myFriendListJPanel,myStrangerListJPanel,BlackListJpanel;
	static final int FRIENDCOUNT=51;
	static final int STRANGER=21;
	static final int BLACK=101;
	JLabel[] myFriendJLabel=new JLabel[FRIENDCOUNT];//对象数组
	JLabel[] myStrangerJLabel=new JLabel[STRANGER];
	JLabel[] BlackListJLabel=new JLabel[BLACK];
	
	JPanel myStrangerBlackListJPanel;
	JButton myStrangerJButton;
	JButton blackListJButton;
	
	JButton addFriendJButton;
	JButton clearFriendJButton;
	JPanel FriendAndMenuPanel;
	
	JPanel myStrangerPanel;
	
	JPanel myFriendStrangerPanel;
	JButton myFriendJButton1;
	JButton myStrangerJButton1;
	
	JButton blackListJButton1;
	
	JPanel BlackList;
	JPanel blacklist;
	JPanel fsb;
	JButton friend;
	JButton stranger;
	JButton black;
	String userName;
	
	JPanel FunctionMenuPanel;
	JPanel  menuPanel;
	JButton functionMenuButton;
	JButton myFriendJButton4;
	JButton myStrangerJButton4;
	JButton blackJButton4;
	
	public FriendList(String userName,String friendString){
		this.userName=userName;
		this.setTitle(userName);
		//第一张卡片
		myFriendPanel=new JPanel(new BorderLayout());//边界布局
		
		functionMenuButton=new JButton("功能菜单");
		myFriendJButton=new JButton("我的好友");
		functionMenuButton.addActionListener(this);
		
		
		FriendAndMenuPanel=new JPanel(new GridLayout(2,1));//网格布局
	
		FriendAndMenuPanel.add(functionMenuButton);
		FriendAndMenuPanel.add(myFriendJButton);
		
		
		myFriendPanel.add(FriendAndMenuPanel,"North");
		
		//中部
		myFriendListJPanel=new JPanel();
		updateFriendIcon(friendString,userName);
		
		
		//myFriendJLabel[Integer.parseInt(userName)].setEnabled(true);
		myFriendScrollPane=new JScrollPane(myFriendListJPanel);
		myFriendPanel.add(myFriendScrollPane);
		
		
		myStrangerBlackListJPanel=new JPanel(new GridLayout(2,1));//网络布局
		myStrangerJButton=new JButton("我的陌生人");
		//添加事件监听器
		myStrangerJButton.addActionListener(this);
		
		blackListJButton=new JButton("黑名单");
		blackListJButton.addActionListener(this);
		myStrangerBlackListJPanel.add(myStrangerJButton);
		myStrangerBlackListJPanel.add(blackListJButton);
		myFriendPanel.add(myStrangerBlackListJPanel,"South");
		
		//第二张卡片
		myStrangerPanel = new JPanel(new BorderLayout());
		
		myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
		
	
		myFriendJButton1=new JButton("我的好友");//添加监听器
		myFriendJButton1.addActionListener(this);
		myStrangerJButton1=new JButton("我的陌生人");
		myFriendStrangerPanel.add(myFriendJButton1);
		myFriendStrangerPanel.add(myStrangerJButton1);
		myStrangerPanel.add(myFriendStrangerPanel,"North");
		
		myStrangerListJPanel=new JPanel(new GridLayout(STRANGER-1,1));
		for(int i=1;i<STRANGER;i++)
		{
			myStrangerJLabel[i]=new JLabel(i+"",new ImageIcon("images/haooyoutou.jpg"),JLabel.LEFT);//"2"
			myStrangerJLabel[i].addMouseListener(this);
			myStrangerListJPanel.add(myStrangerJLabel[i]);
		}
		myStrangerScrollPane=new JScrollPane(myStrangerListJPanel);
		myStrangerPanel.add(myStrangerScrollPane);
		
		
		blackListJButton1=new JButton("黑名单");
		blackListJButton1.addActionListener(this);
		myStrangerPanel.add(blackListJButton1,"South");
		
		
		
		//第三张卡
		BlackList=new JPanel(new BorderLayout());
		fsb=new JPanel(new GridLayout(3,1));//网络布局
		friend=new JButton("我的好友");
		friend.addActionListener(this);
		stranger=new JButton("陌生人");
		stranger.addActionListener(this);
		black=new JButton("黑名单");
		fsb.add(friend);
		fsb.add(stranger);
		fsb.add(black);
		BlackList.add(fsb,"North");
		blacklist=new JPanel(new GridLayout(BLACK-1,1));
		for(int i=1;i<BLACK;i++)
		{
			BlackListJLabel[i]=new JLabel(i+"",new ImageIcon("images/timg.jpg"),JLabel.LEFT);//"2"
			BlackListJLabel[i].addMouseListener(this);
			blacklist.add(BlackListJLabel[i]);
		}
		BlackListScrollpane=new JScrollPane(blacklist);
		BlackList.add(BlackListScrollpane);
		//功能菜单
		FunctionMenuPanel=new JPanel(new BorderLayout());//边界布局

		
		myFriendJButton4=new JButton("我的好友");
		myFriendJButton4.addActionListener(this);
		myStrangerJButton4=new JButton("陌生人");
		myStrangerJButton4.addActionListener(this);
		blackJButton4=new JButton("黑名单");
		blackJButton4.addActionListener(this);
		addFriendJButton=new JButton("添加好友");
		addFriendJButton.addActionListener(this);
		clearFriendJButton=new JButton("删除好友");
		clearFriendJButton.addActionListener(this);
		menuPanel=new JPanel(new GridLayout(5,1));
		menuPanel.add(addFriendJButton);
		menuPanel.add(clearFriendJButton);
		menuPanel.add(myFriendJButton4);
		menuPanel.add(myStrangerJButton4);
		menuPanel.add(blackJButton4);
		
		FunctionMenuPanel.add(menuPanel,"North");
		
		cardLayout=new CardLayout();
		this.setLayout(cardLayout);
		this.add(myFriendPanel,"1");
		this.add(myStrangerPanel,"2");
		this.add(BlackList,"3");
		this.add(FunctionMenuPanel,"4");
		
		this.setSize(280,750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


	public void updateFriendIcon(String friendString,String userName) {
		/*JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem seekFriend = new JMenuItem("images/timg.jpg");
		seekFriend .addActionListener(new MenuActionListener());
		
		// 弹出菜单菜单项的添加
		popupMenu.add(seekFriend);
		*/
		
		myFriendListJPanel.removeAll();//移除全部好友图标
		String[] friendName=friendString.split(" ");
		int count=friendName.length;
		
		myFriendListJPanel.setLayout(new GridLayout(count, 1));
		for(int i=0;i<count;i++)
		{
			myFriendJLabel[i]=new JLabel(friendName[i],new ImageIcon("images/fuhua.gif"),JLabel.LEFT);//"1"
			//myFriendJLabel[i].setEnabled(false);
			// 将弹出菜单添加到多行文本框
			/*myFriendJLabel[i].add(popupMenu);//showText为弹出右键菜单的组件的对象
			myFriendJLabel[i].addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger())
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
			
			}
			public void mouseReleased(MouseEvent e) {
			if (e.isPopupTrigger())
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
			}
			);*/
			myFriendJLabel[i].addMouseListener(this);
			myFriendListJPanel.add(myFriendJLabel[i]);
		}
	}
	
	
	public static void main(String[] args){
		//FriendList friendList=new FriendList();
		
	}
	public void setEnableFriendIcon(String friendString) {
		String[] friendName=friendString.split(" ");
		int count=friendName.length;
		for(int i=1;i<count;++i)
		{
			myFriendJLabel[Integer.parseInt(friendName[i])].setEnabled(true);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent argo){
		if(argo.getSource()==clearFriendJButton){
			String clearFriendName=JOptionPane.showInputDialog(null,"请输入好友名字","删除好友",JOptionPane.DEFAULT_OPTION);
			Message mess=new Message();
			mess.setSender(userName);
			mess.setReceiver("Server");
			mess.setContent(clearFriendName);
			mess.setMessageType(Message.message_ClearFriend);
			
			Socket s=(Socket)ClientConnect.hmSocket.get(userName);
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(mess);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}cardLayout.show(this.getContentPane(), "1");
		}
		
		
		
		if(argo.getSource()==addFriendJButton){
			String addFriendName=JOptionPane.showInputDialog(null,"请输入好友名字","添加好友",JOptionPane.DEFAULT_OPTION);
			Message mess=new Message();
			mess.setSender(userName);
			mess.setReceiver("Server");
			mess.setContent(addFriendName);
			mess.setMessageType(Message.message_AddFriend);
			
			Socket s=(Socket)ClientConnect.hmSocket.get(userName);
			ObjectOutputStream oos;
			try {
				oos = new ObjectOutputStream(s.getOutputStream());
				oos.writeObject(mess);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cardLayout.show(this.getContentPane(), "1");
		}
		
		if(argo.getSource()==functionMenuButton){
			cardLayout.show(this.getContentPane(), "4");
		}
		if(argo.getSource()==myStrangerJButton4){
			cardLayout.show(this.getContentPane(), "2");
		}
		if(argo.getSource()==myFriendJButton4){
			cardLayout.show(this.getContentPane(), "1");
		}
		if(argo.getSource()==blackJButton4){
			cardLayout.show(this.getContentPane(), "3");
		}
		if(argo.getSource()==myStrangerJButton){
			cardLayout.show(this.getContentPane(), "2");
		}
		if(argo.getSource()==myFriendJButton1){
			cardLayout.show(this.getContentPane(), "1");
			
		}
		if(argo.getSource()==blackListJButton){
			cardLayout.show(this.getContentPane(), "3");
		}
		if(argo.getSource()==blackListJButton1){
			cardLayout.show(this.getContentPane(), "3");
		}
		if(argo.getSource()==friend){
			cardLayout.show(this.getContentPane(), "1");
		}
		if(argo.getSource()==stranger){
			cardLayout.show(this.getContentPane(), "2");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getClickCount()==2){
			JLabel jlbl=(JLabel)arg0.getSource();
			String receiver =jlbl.getText();
			//new FriendChatClient(this.userName,receiver);
			//new Thread(new FriendChat(this.userName,receiver)).start();
			
			FriendChat1 friendChat1=(FriendChat1)hmFriendChat1.get(userName+"to"+receiver);
			if(friendChat1==null){
			friendChat1=new FriendChat1(this.userName,receiver);//friendChat是一个变量
			hmFriendChat1.put(userName+"to"+receiver,friendChat1);//保存对象到HashMap中
			}else{
				friendChat1.setVisible(true);
			}
			
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JLabel jLabel=(JLabel)arg0.getSource();
		jLabel.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		JLabel jLabel=(JLabel)arg0.getSource();
		jLabel.setForeground(Color.black);
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}