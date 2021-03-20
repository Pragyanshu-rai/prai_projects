// Gui Chat Application 

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class MakeFrame extends JFrame implements ActionListener,Runnable,WindowListener
{
	private ImageIcon ic = new ImageIcon("logo.png");
	private JPanel left = new JPanel(),right = new JPanel();
	private JLabel username = new JLabel("     USERNAME"),hidden = new JLabel(),ipl = new JLabel("   IP ADDRESS  ");
	private JButton send = new JButton("SEND"),connect = new JButton("CONNECT"),close= new  JButton("DISCONNECT"),mini=new JButton("-");
	private JTextField sendta= new JTextField(),iptext=new JTextField(),usr= new JTextField();
	private JTextArea recieveta= new JTextArea();
	private JScrollPane sp=new JScrollPane(recieveta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED),sp2 = new JScrollPane(sendta,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JRadioButton serverb = new JRadioButton("   SERVER");
	private ServerSocket server;
	private Socket client;
	private BufferedReader in;
	private PrintWriter out;
	private String ip="192.168.1.14",msg=new String(),runame = new String(),luname = new String(),msgo = new String();
	private int Port = 9069;
	private boolean isServer = false,stop = false;
	public MakeFrame(String s)
	{
		super(s);
		//revalidate();
		//setUndecorated(true);
		addWindowListener(this);
		setIconImage(ic.getImage());
		setBounds(250,100,650,460);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLayout(null);
	}
	public void create()
	{
		left.setBounds(0,0,450,450);
		left.setBackground(Color.DARK_GRAY);
		left.setLayout(null);
		sp.setBounds(10,10,430,300);
		recieveta.setLineWrap(true);
		recieveta.setFont(new Font("Calibri",Font.PLAIN,20));
		recieveta.setCaretColor(Color.WHITE);
		recieveta.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		sp2.setBounds(10,320,430,90);
		sendta.setFont(new Font("Calibri",Font.PLAIN,20));
		sendta.addActionListener(e -> readAndSendText(false));
		//sendta.setLineWrap(true);
		left.add(sp);		
		left.add(sp2);
		right.setBounds(450,0,200,450);
		right.setBackground(Color.GRAY);
		right.setLayout(null);
		connect.setBounds(10,290,180,50);
		connect.setFocusPainted(false);
		connect.setBackground(Color.GRAY);
		connect.setFont(new Font("Calibri",Font.PLAIN,20));
		connect.addActionListener(e -> {
			Thread t =  new Thread(this);
			try{
			t.sleep(1000);
			t.start();}
			catch(Exception ae)
			{}
		});//lambda function 
		//connect.setEnabled(false);
		send.setBounds(10,360,180,50);
		send.setFocusPainted(false);
		send.setBackground(Color.GRAY);
		send.setFont(new Font("Calibri",Font.PLAIN,20));
		send.addActionListener(e -> readAndSendText(false));
		send.setEnabled(false);
		mini.setBounds(100,0,50,40);
		mini.setFont(new Font("Calibri",Font.BOLD,20));
		mini.setBorder(null);
		mini.setBackground(Color.GRAY);
		mini.setFocusPainted(false);
		mini.addActionListener(e -> setState(this.ICONIFIED));
		close.setBorder(null);
		close.setBackground(Color.GRAY);
		close.setFocusPainted(false);
		close.setBounds(10,10,180,30);
		close.setFont(new Font("Calibri",Font.BOLD,20));
		close.addActionListener(e -> {
		stop=true;
		readAndSendText(true);	
		});
		close.setEnabled(false);
		username.setBounds(10,40,180,40);
		username.setFont(new Font("Calibri",Font.PLAIN,20));
		usr.setBounds(10,80,180,30);
		//usr.setBorder(null);
		usr.setCaretColor(Color.GRAY);
		usr.setBackground(Color.GRAY);
		usr.setFont(new Font("Calibri",Font.PLAIN,20));
		serverb.setBounds(25,120,150,40);
		serverb.setBackground(Color.GRAY);
		serverb.setFont(new Font("Calibri",Font.PLAIN,20));
		serverb.setFocusPainted(false);
		serverb.addActionListener(e -> {
			if(serverb.isSelected())
				{iptext.setEditable(false);
				isServer = true;}
			else
				{iptext.setEditable(true);
				isServer = false;}
		});
		hidden.setBounds(10,160,180,40);
		hidden.setBackground(Color.GRAY);
		hidden.setFont(new Font("Calibri",Font.PLAIN,14));
		ipl.setBounds(20,200,160,40);
		ipl.setFont(new Font("Calibri",Font.PLAIN,20));
		iptext.setBounds(10,240,180,30);
		iptext.setCaretColor(Color.GRAY);
		iptext.setBackground(Color.GRAY);
		iptext.setFont(new Font("Calibri",Font.PLAIN,20));
		iptext.setText(ip);
		right.add(close);
		//right.add(mini);
		right.add(username);
		right.add(usr);
		right.add(serverb);
		right.add(hidden);
		right.add(ipl);
		right.add(iptext);
		right.add(connect);
		right.add(send);
		add(left);
		add(right);
		setVisible(true);
		//this.revalidate();
	}
	public void actionPerformed(ActionEvent ae)
	{
		
	}
	public void windowOpened(WindowEvent we)
		{
			JOptionPane.showMessageDialog(this,"INSTRUCTIONS\n\nServer\n  1.Enter Your Name In The Username Section\n  2.Enter Your IP Address.\n  3.Select the Server Option\n  4.Then Press Connect\n\nClient\n  1.Enter Your Name In The Username Section\n  2.Enter Your IP Address.\n  3.Then Press Connect\n\nNote:-\n  1.Server Must Press Connect First And Then The Client\n  2.Press Disconnect To Disconnect\n  3.Both The System Must Be On The Same Network.");
		}
		public void windowClosed(WindowEvent we)
		{

		}
		public void windowClosing(WindowEvent we)
		{

		}
		public void windowActivated(WindowEvent we)
		{

		}
		public void windowDeactivated(WindowEvent we)
		{

		}
		public void windowIconified(WindowEvent we)
		{

		}
		public void windowDeiconified(WindowEvent we)
		{

		}
	public void clientSide(String mode)
	{
		try 
		{
			recieveta.setText("");
			ip = mode;
			luname = usr.getText();
			client =new Socket(ip,Port);
			hidden.setText("Connection Established");
			out = new PrintWriter(client.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			runame = in.readLine();
			if(runame=="")
				runame = "Server";
			setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
			out.println(luname);
			connect.setEnabled(false);
			close.setEnabled(true);
			iptext.setEditable(false);
			serverb.setEnabled(false);
			usr.setEditable(false);
			send.setEnabled(true);
			readIncomingMsg();
		}
		catch(IOException ioe)
		{
			//recieveta.setText("ERR.....I/O "+ioe.getMessage());
			hidden.setText("Server Offline..");
			sendta.setText("Exception:- "+ioe.getMessage());
		}
		catch(Exception e)
		{
			hidden.setText("Disconnected");
		}	
	}
	public void serverSide()
	{
		try 
		{
			luname = usr.getText();
			server = new ServerSocket(Port);	
			client = server.accept();
			hidden.setText("Connection Established");
			out = new PrintWriter(client.getOutputStream(),true);
			out.println(luname);
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			runame = in.readLine();
			if(runame.equals(""))
				runame = "client";
			setDefaultCloseOperation(this.DO_NOTHING_ON_CLOSE);
			connect.setEnabled(false);
			close.setEnabled(true);
			iptext.setEditable(false);
			serverb.setEnabled(false);
			usr.setEditable(false);
			send.setEnabled(true);
			readIncomingMsg();
		}
		catch(IOException ioe)
		{
			recieveta.setText("ERR.....I/O "+ioe.getMessage());
		}
		catch(Exception e)
		{
			hidden.setText("Disconnected");
		}		
	}
	public void readAndSendText(boolean b)
	{
		try 
		{
				if(b==true)
				{
					out.println("69Disconnected69");
					clear();
				}	
				msgo = sendta.getText();
				if(msgo.equals(""))
				{}
				else
				{
					out.println(msgo);
					sendta.setText("");
					recieveta.setText(recieveta.getText()+"you: "+msgo+"\n");
				}
		}
		catch(Exception e)
		{
			hidden.setText("Disconnected");
		}
	}
	public void readIncomingMsg()
	{
		try 
		{
			while(!stop)
			{
				msg=in.readLine();
				if("69Disconnected69".equals(msg)){
					stop=true;
					hidden.setText("Closing Ports.....");
					out.println(" ");//close ko pehle disable kar bsdk
					JOptionPane.showMessageDialog(this," "+runame+" Just Went Offline","CONNECTION LOST!!!!",JOptionPane.WARNING_MESSAGE);
					clear();
				}
				else if("".equals(msg)){}//recieveta.setText(recieveta.getText()+"Empty hai"+"\n");}
				else
				{
					recieveta.setText(recieveta.getText()+runame+": "+msg+"\n");
				}			
			}
		}
		catch(IOException ioe)
		{
			hidden.setText("ERR.....r");
		}
		catch(Exception e)
		{
			hidden.setText("Disconnected");
		}
	}
	public void run()
	{
		if(isServer)
			{
				hidden.setText("Waiting For A Connection....");
				serverSide();
			}
			else
			{
				hidden.setText("Waiting For Connection....");
				clientSide(iptext.getText());
				iptext.setEnabled(false);
			}
	}
	public void clear()
	{
		try{
		in.close();
		out.close();
		client.close();
		server.close();
		}
		catch(Exception ex)
		{
			System.out.println("Could not close the streams!!!!!");
		}
		finally
		{
			System.exit(0);
		}
	}
}
public class Chat
{
	public static void main(String []arg)
	{
		MakeFrame fr = new MakeFrame("Chat Ley");
		fr.create();
	}
}
