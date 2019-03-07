package zzchatclient;
import java.awt.*;
import javax.swing.*;
public class ClientLogin extends JFrame{
 JLabel jlbl1,jlbl2,jlbl3,jlbl4,jlbl5;
 JTabbedPane jtp1;
 JButton jb1,jb2,jb3,jb4;
 JPanel jp1,jp2,jp3,jp4;
 JTextField jtf1;
 JPasswordField jpf1;
 JCheckBox jcb1,jcb2;
 
 
 public ClientLogin(){
  jlbl1=new JLabel(new ImageIcon("images/tou.gif"));
  //创建北部组件
  this.add(jlbl1,"North");
  
  //创建中部组件
  jp2=new JPanel(new GridLayout(3,3));//布局
  jp3=new JPanel();jp4=new JPanel();
  jlbl2=new JLabel("ZZ号码",JLabel.CENTER);
  jlbl3=new JLabel("ZZ密码",JLabel.CENTER);
  jlbl4=new JLabel("忘记密码",JLabel.CENTER);
  jlbl4.setForeground(Color.blue);
  jlbl5=new JLabel("申请密码保护",JLabel.CENTER);
  jtf1=new JTextField();
  jpf1=new JPasswordField();
  jb4=new JButton(new ImageIcon("images/clear.gif"));
  jcb1=new JCheckBox("隐身登陆");
  jcb2=new JCheckBox("记住密码");
  jp2.add(jlbl2); jp2.add(jtf1); jp2.add(jb4);
  jp2.add(jlbl3); jp2.add(jpf1); jp2.add(jlbl4);
  jp2.add(jcb1); jp2.add(jcb2); jp2.add(jlbl5);
  jtp1=new JTabbedPane();
  jtp1.add(jp2,"ZZ号码");jtp1.add(jp3,"手机号码");jtp1.add(jp4,"电子邮箱");
  this.add(jtp1);
  
  //创建南部组件
  jb1=new JButton(new ImageIcon("images/denglu.gif"));
  jb2=new JButton(new ImageIcon("images/zhuce.gif"));
  jb3=new JButton(new ImageIcon("images/quxiao.gif"));
  jp1=new JPanel();
  jp1.add(jb1);jp1.add(jb2);jp1.add(jb3);
  this.add(jp1,"South");
  
  this.setSize(350,240);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setVisible(true);
 }
 
 
 public static void main(String[] args) {
  ClientLogin clientLogin=new ClientLogin();
 }
}