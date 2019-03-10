package zzchatclient;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class friendlist extends JFrame implements ActionListener,MouseListener{
 JScrollPane myFriendScrollpane;
 JPanel myFriendListPanel;
 static final int FRIENDCOUNT=51;
 JLabel[] myFriendLabel=new JLabel[FRIENDCOUNT];
 
 JScrollPane myStrangerScrollpane1;
 JPanel myStrangerListPanel;
 static final int STRANGERCOUNT=21;
 JLabel[] myStrangerLabel=new JLabel[STRANGERCOUNT];
 //第一张卡片组件
 JPanel myFriendPanel;
 JButton myFriendButton;
 
 JPanel myStrangerBlacklistPanel;
 JButton myStrangerButton;
 
 JButton BlackListButton;
 
 //第二张卡片组件
 JPanel StrangerPanel;
 JPanel myFriendStrangerPanel;
 JPanel BlacklistPanel;
 JButton myFriendButton1;
 JButton myStrangerButton1;
 JButton BlackListButton1;
 
 //第三张卡片组件
 JPanel blacklistpanel;
 JPanel fsblist;
 JButton friend;
 JButton stranger;
 JButton black;
 JScrollPane blackScrollpane;
 JPanel blackPanel;
 static final int BLACKCOUNT=11;
 JLabel[] blackLabel=new JLabel[BLACKCOUNT];
 
 
 //卡片布局
 CardLayout cardLayout;
 
 public friendlist(){
  //第一张卡片
  myFriendPanel=new JPanel(new BorderLayout());//边界布局
  myFriendButton=new JButton("我的沙雕网友");
  myFriendPanel.add(myFriendButton,"North");
  
  myStrangerBlacklistPanel=new JPanel(new GridLayout(2,1));
  myStrangerButton=new JButton("不认识的沙雕网友");
  //添加事件监听器
  myStrangerButton.addActionListener(this);
  
  myStrangerBlacklistPanel.add(myStrangerButton);
  BlackListButton=new JButton("不想认识的沙雕网友");
  BlackListButton.addActionListener(this);
  myStrangerBlacklistPanel.add(BlackListButton);
  myFriendPanel.add(myStrangerBlacklistPanel,"South");
  
  myFriendListPanel=new JPanel(new GridLayout(FRIENDCOUNT-1,1));
  for(int i=1;i<FRIENDCOUNT;i++)
  {
   myFriendLabel[i]=new JLabel(i+"",new ImageIcon("images/fuhua.gif"),JLabel.LEFT);
   myFriendLabel[i].addMouseListener(this);
   myFriendListPanel.add(myFriendLabel[i]);
  }
  myFriendScrollpane=new JScrollPane(myFriendListPanel);
  myFriendPanel.add(myFriendScrollpane);
  //第二张卡片
  StrangerPanel=new JPanel(new BorderLayout());//边界布局
  
  myFriendStrangerPanel=new JPanel(new GridLayout(2,1));
  myFriendButton1=new JButton("我的沙雕网友");
  //添加事件监听器
  myFriendButton1.addActionListener(this);
  
  myStrangerButton1=new JButton("不认识的沙雕网友");
  myFriendStrangerPanel.add(myFriendButton1);
  myFriendStrangerPanel.add(myStrangerButton1);
  StrangerPanel.add(myFriendStrangerPanel,"North");
  
  BlackListButton1=new JButton("不想认识的沙雕网友");
  BlackListButton1.addActionListener(this);
  StrangerPanel.add(BlackListButton1,"South");
  
  
  myStrangerListPanel=new JPanel(new GridLayout(STRANGERCOUNT-1,1));
  for(int i=1;i<STRANGERCOUNT;i++)
  {
   myStrangerLabel[i]=new JLabel(i+"",new ImageIcon("images/fuhua.gif"),JLabel.LEFT);
   myStrangerLabel[i].addMouseListener(this);
   myStrangerListPanel.add(myStrangerLabel[i]);
  }
  myStrangerScrollpane1=new JScrollPane(myStrangerListPanel);
  StrangerPanel.add(myStrangerScrollpane1);
  
  //第三张卡片
  blacklistpanel=new JPanel(new BorderLayout());
  fsblist=new JPanel(new GridLayout(3,1));
  friend=new JButton ("我的沙雕网友");
  friend.addActionListener(this);
  stranger=new JButton("不认识的沙雕网友");
  stranger.addActionListener(this);
  black=new JButton("不想认识的沙雕网友");
  fsblist.add(friend);
  fsblist.add(stranger);
  fsblist.add(black);
  blacklistpanel.add(fsblist,"North");
  blackPanel=new JPanel(new GridLayout(BLACKCOUNT-1,1));
  for(int i=1;i<BLACKCOUNT;i++)
  {
	  blackLabel[i]=new JLabel(i+"",new ImageIcon("images/fuhua.gif"),JLabel.LEFT);
      blackLabel[i].addMouseListener(this);
      blackPanel.add(blackLabel[i]);
  }
  blackScrollpane=new JScrollPane(blackPanel);
  blacklistpanel.add(blackScrollpane);
  
  cardLayout=new CardLayout();
  this.setLayout(cardLayout);
  this.add(myFriendPanel,"1");
  this.add(StrangerPanel,"2");
  this.add(blacklistpanel,"3");
  this.setSize(200,500);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setVisible(true);
  }
 public static void main(String[] args) {
  friendlist Friendlist=new friendlist();
 }
 @Override
 public void actionPerformed(ActionEvent arg0) {
  if (arg0.getSource()==myStrangerButton){
   cardLayout.show(this.getContentPane(),"2");
  }
  if (arg0.getSource()==myFriendButton1){
   cardLayout.show(this.getContentPane(),"1");
  }
  if (arg0.getSource()==BlackListButton){
	   cardLayout.show(this.getContentPane(),"3");
	  }
  if (arg0.getSource()==BlackListButton1){
	   cardLayout.show(this.getContentPane(),"3");
	  }
  if (arg0.getSource()==friend){
	   cardLayout.show(this.getContentPane(),"1");
	  }
  if (arg0.getSource()==stranger){
	   cardLayout.show(this.getContentPane(),"2");
	  }
 }
 @Override
 public void mouseClicked(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }
 @Override
 public void mouseEntered(MouseEvent e) {
  JLabel jLabel =(JLabel)e.getSource();
    jLabel.setForeground(Color.BLUE);
  
 }
 @Override
 public void mouseExited(MouseEvent e) {
  JLabel jLabel =(JLabel)e.getSource();
  jLabel.setForeground(Color.BLACK);
  
 }
 @Override
 public void mousePressed(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }
 @Override
 public void mouseReleased(MouseEvent e) {
  // TODO Auto-generated method stub
  
 }
}