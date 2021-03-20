import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class TTT extends JFrame implements ActionListener,WindowListener,MouseListener,Runnable
{
	private int[] done= new int[9],visit = new int[18];
	private int st=-1,pg,opg,cnt=0,sc1=0,sc2=0,cmp=0,dan=0;
	private boolean play=true;
	private JPanel up=new JPanel(),down=new JPanel();
	private JButton b1=new JButton(""),b2=new JButton(""),b3=new JButton(""),b4=new JButton(""),b5=new JButton(""),b6=new JButton(""),b7=new JButton(""),b8=new JButton(""),b9=new JButton("");
	private JTextField p1=new JTextField("Player-1"),p2=new JTextField();
	private String pl1=new String("X"),pl2=new String("O"),cp=new String(),pn1=new String(),pn2=new String();
	private Font fnt = new Font("Calibri",Font.BOLD,120);
	private Color bc=b1.getBackground();
	public TTT(String s)
	{
		super(s);
		setIconImage(new ImageIcon("tic-tac-toe.jpg").getImage());
		addWindowListener(this);
		setBounds(450,150,450,440);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setLayout(null);
		up.setBounds(0,0,450,60);
		up.setBackground(Color.DARK_GRAY);
		up.setLayout(new GridLayout(1,1,5,5));
		down.setBounds(0,60,450,345);
		down.setBackground(Color.GREEN);
		down.setLayout(new GridLayout(3,3));
		for(int i=0;i<9;i++)
			done[i]=0;
		for(int i=0;i<18;i++)
			visit[i]=0;
	}
	public void run()
	{
		create();
	}
	public void create()
	{
		p1.setFont(new Font("Calibri",Font.BOLD,30));
		p2.setFont(new Font("Calibri",Font.BOLD,30));
		p1.setBorder(null);
		p2.setBorder(null);
		p1.addMouseListener(this);
		p1.addActionListener(ae -> {/*p1.setText(" "+p1.getText());*/p2.requestFocus(true);});//to set the focus on the next text field
		p2.addActionListener(e -> {//p2.setText(" "+p2.getText());
			if(st==-1)
			{
				st=0;
				if(p2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this,"😎️    "+p1.getText()+" you are 'X'.");
					pl1="X";
					pl2="O";
					p1.setText(p1.getText()+": X");
					pn1=p1.getText();
					cmp=1;
					p2.setText("PC:O");
					pn2=p2.getText();
					p1.setEditable(false);
					p2.setEditable(false);
					p1.setBackground(Color.GREEN);
					pg=1;
					opg=pg;
				}
				else
				{
					int x = JOptionPane.showConfirmDialog(this,p1.getText()+" do you choose X?"," X or O ",JOptionPane.YES_NO_OPTION);
					if(x==JOptionPane.YES_OPTION)
					{
						pl1="X";
						pl2="O";
						p1.setText(p1.getText()+": X");
						pn1=p1.getText();
						p2.setText(p2.getText()+": O");
						pn2=p2.getText();
						p1.setEditable(false);
						p2.setEditable(false);
						p1.setBackground(Color.GREEN);
						pg=1;
						opg=pg;
					}
					else
					{
						pl1="O";
						pl2="X";
						p1.setText(p1.getText()+": O");
						pn1=p1.getText();
						p2.setText(p2.getText()+": X");
						pn2=p2.getText();	
						p1.setEditable(false);
						p2.setEditable(false);
						p2.setBackground(Color.GREEN);
						pg=2;
						opg=pg;
					}
				}
			}
		});
		up.add(p1);
		up.add(p2);
		b1.setFocusPainted(false);
		b2.setFocusPainted(false);
		b3.setFocusPainted(false);
		b4.setFocusPainted(false);
		b5.setFocusPainted(false);
		b6.setFocusPainted(false);
		b7.setFocusPainted(false);
		b8.setFocusPainted(false);
		b9.setFocusPainted(false);
		b1.setFont(fnt);
		//b1.setBackground(new Color(120,34,57,80));
		b2.setFont(fnt);
		b3.setFont(fnt);
		b4.setFont(fnt);
		b5.setFont(fnt);
		b6.setFont(fnt);
		b7.setFont(fnt);
		b8.setFont(fnt);
		b9.setFont(fnt);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		down.add(b1);
		down.add(b2);
		down.add(b3);
		down.add(b4);
		down.add(b5);
		down.add(b6);
		down.add(b7);
		down.add(b8);
		down.add(b9);
		add(up);
		add(down);
		setResizable(false);
		setVisible(true);
	}
	public void check(String ch,int cnt)
	{
		if(ch.equals(b1.getText())&&ch.equals(b2.getText())&&ch.equals(b3.getText()))
			won(ch,1);
		else if(ch.equals(b4.getText())&&ch.equals(b5.getText())&&ch.equals(b6.getText()))
			won(ch,2);
		else if(ch.equals(b7.getText())&&ch.equals(b8.getText())&&ch.equals(b9.getText()))
			won(ch,3);
		else if(ch.equals(b1.getText())&&ch.equals(b4.getText())&&ch.equals(b7.getText()))
			won(ch,4);
		else if(ch.equals(b2.getText())&&ch.equals(b5.getText())&&ch.equals(b8.getText()))
			won(ch,5);
		else if(ch.equals(b3.getText())&&ch.equals(b6.getText())&&ch.equals(b9.getText()))
			won(ch,6);
		else if(ch.equals(b1.getText())&&ch.equals(b5.getText())&&ch.equals(b9.getText()))
			won(ch,7);
		else if(ch.equals(b7.getText())&&ch.equals(b5.getText())&&ch.equals(b3.getText()))
			won(ch,8);
		else if(cnt==9)
			won(null,-1);
	}
	public void won(String w,int col)
	{	
		if(col==-1)
		{
			//JOptionPane.showMessageDialog(this,"       X-O Draw 😀️");
			int x=JOptionPane.showConfirmDialog(this,"           X-O Draw\n Wanna Play Again 😏️ ?","Tell me",JOptionPane.YES_NO_OPTION);
			if(x==JOptionPane.YES_OPTION)
				reset(true,0);
			else
				System.exit(0);
		}
		else
		{
			reset(false,col);
		//	JOptionPane.showMessageDialog(this,"           "+w+" Wins 😀️");
			if(w.equals("X"))
			{
				if(opg==1)
				{
					sc1++;
					p1.setText(sc1+"-"+pn1);
				}
				else
				{
					sc1++;
					p2.setText(sc1+"-"+pn2);
				}
			}
			else if(w.equals("O"))
			{
				if(opg==1)
				{
					sc2++;
					p2.setText(sc2+"-"+pn2);
				}
				else
				{
					sc2++;
					p1.setText(sc2+"-"+pn1);
				}
			}
			int x=JOptionPane.showConfirmDialog(this,"           "+w+" Wins 😀️\nWanna Play Again 😏️ ?","Tell me",JOptionPane.YES_NO_OPTION);
			if(x==JOptionPane.YES_OPTION)
				reset(true,0);
			else
				System.exit(0);
		}
	}
	public void reset(boolean rst,int col)
	{
		
		if(rst==true&&col==0)
		{
			st=0;
			cnt=0;
			pg=opg;			
			if(cmp==1)
			{
				for(int i=0;i<18;i++)
				{	
					if(i<9)
						done[i]=0;
					visit[i]=0;
				}
			}
			if(pg==2)
			{
				p2.setBackground(Color.GREEN);
				p1.setBackground(Color.WHITE);
				pg--;			
			}
			else if(pg==1)
			{
				p1.setBackground(Color.GREEN);
				p2.setBackground(Color.WHITE);
				pg++;
			}
			b1.setEnabled(true);
			b1.setText(null);
			b2.setEnabled(true);
			b2.setText(null);
			b3.setEnabled(true);
			b3.setText(null);
			b4.setEnabled(true);
			b4.setText(null);
			b5.setEnabled(true);
			b5.setText(null);
			b6.setEnabled(true);
			b6.setText(null);
			b7.setEnabled(true);
			b7.setText(null);
			b8.setEnabled(true);
			b8.setText(null);
			b9.setEnabled(true);
			b9.setText(null);
			b1.setBackground(bc);
			b2.setBackground(bc);
			b3.setBackground(bc);
			b4.setBackground(bc);
			b5.setBackground(bc);
			b6.setBackground(bc);
			b7.setBackground(bc);
			b8.setBackground(bc);
			b9.setBackground(bc);
		}
		else
		{
			switch(col)
			{
				case 1:b1.setBackground(Color.GREEN);b2.setBackground(Color.GREEN);b3.setBackground(Color.GREEN);break;
				case 2:b4.setBackground(Color.GREEN);b5.setBackground(Color.GREEN);b6.setBackground(Color.GREEN);break;
				case 3:b7.setBackground(Color.GREEN);b8.setBackground(Color.GREEN);b9.setBackground(Color.GREEN);break;
				case 4:b1.setBackground(Color.GREEN);b4.setBackground(Color.GREEN);b7.setBackground(Color.GREEN);break;
				case 5:b2.setBackground(Color.GREEN);b5.setBackground(Color.GREEN);b8.setBackground(Color.GREEN);break;
				case 6:b3.setBackground(Color.GREEN);b6.setBackground(Color.GREEN);b9.setBackground(Color.GREEN);break;
				case 7:b1.setBackground(Color.GREEN);b5.setBackground(Color.GREEN);b9.setBackground(Color.GREEN);break;
				case 8:b3.setBackground(Color.GREEN);b5.setBackground(Color.GREEN);b7.setBackground(Color.GREEN);break;
				default: System.out.println("in default");break;
			}
		}
	}
	//overriding all the functions from Action,window and Focus listener interface 10 in total
	public void actionPerformed(ActionEvent ae)
	{
		if(st==0||st%2==0)
		{
			cp="X";
			st++;
		}
		else
		{
			cp="O";
			st++;
		}
		if(ae.getSource()==b1)
		{
			b1.setText(cp);
			b1.setEnabled(false);
			done[0]=-1;
			cnt++;
		}
		else if(ae.getSource()==b2)
		{
			b2.setText(cp);
			b2.setEnabled(false);
			done[1]=-1;
			cnt++;
		}
		else if(ae.getSource()==b3)
		{
			b3.setText(cp);
			b3.setEnabled(false);
			done[2]=-1;
			cnt++;
		}
		else if(ae.getSource()==b4)
		{
			b4.setText(cp);
			b4.setEnabled(false);
			done[3]=-1;
			cnt++;
		}
		else if(ae.getSource()==b5)
		{
			b5.setText(cp);
			b5.setEnabled(false);
			done[4]=-1;
			cnt++;
		}
		else if(ae.getSource()==b6)
		{
			b6.setText(cp);
			b6.setEnabled(false);
			done[5]=-1;
			cnt++;
		}
		else if(ae.getSource()==b7)
		{
			b7.setText(cp);
			b7.setEnabled(false);
			done[6]=-1;
			cnt++;
		}
		else if(ae.getSource()==b8)
		{
			b8.setText(cp);
			b8.setEnabled(false);
			done[7]=-1;
			cnt++;
		}
		else if(ae.getSource()==b9)
		{
			b9.setText(cp);
			b9.setEnabled(false);
			done[8]=-1;
			cnt++;
		}
		check(cp,cnt);
		if(cmp==1&&st%2!=0)
			comp();
		check(cp,cnt);
		if(pg==2)
		{
			p1.setBackground(Color.GREEN);
			p2.setBackground(Color.WHITE);
			pg--;			
		}
		else if(pg==1)
		{
			p2.setBackground(Color.GREEN);
			p1.setBackground(Color.WHITE);
			pg++;
		}
	}
	public void windowOpened(WindowEvent we)
	{
		JOptionPane.showMessageDialog(this,"INSTRUCTIONS\n\n  Basic-Rules:-\n    1.The First Player to get either 'X' or 'O' in a line(horizontal,vertical or diagonal) wins.\n    2.The player who chooses 'X' must go first.\n\n  Player-Vs-player:-\n    1.Enter your names and press enter and the first player will get to choose either 'X' or 'O'.\n\n  Player-Vs-Computer:-\n    1.Enter your name in the first slot and press enter twice then start the game.\n        Note:- The player must choose 'X' when against Computer.\n\n                                                Please Enjoy The Game!!!!!!😁️");
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
	public void mousePressed(MouseEvent ke)
	{
		if(p1.getText().equals("Player-1"))
			p1.setText(null);
	}
	public void mouseClicked(MouseEvent ke)
	{

	}
	public void mouseReleased(MouseEvent ke)
	{

	}
	public void mouseEntered(MouseEvent me)
	{

	}
	public void mouseExited(MouseEvent me)
	{

	}
	public int comp()
	{
		cnt++;
		while(play)
		{
		if(st==0||st%2==0)
		{
			cp="X";
			st++;
		}
		else
		{
			cp="O";
			st++;
		}
		if(pg==2)
		{
			p1.setBackground(Color.GREEN);
			p2.setBackground(Color.WHITE);
			pg--;			
		}
		else if(pg==1)
		{
			p2.setBackground(Color.GREEN);
			p1.setBackground(Color.WHITE);
			pg++;
		}
		if(done[4]==0)
		{
			b5.setText("O");
			b5.setEnabled(false);
			done[4]=1;
			return 0;
		}
		//to check if the computer is winning
		dan=0;
		if(done[0]==1)
			dan++;
			if(done[1]==1)
				dan++;
				if(done[2]==1)
					dan++;
					if(dan==2&&visit[0]==0)
					{
						if(done[0]==0)
						{
							b1.setText("O");
							b1.setEnabled(false);visit[0]=1;
							done[0]=1;return 0;
						}
						if(done[1]==0)
						{
							b2.setText("O");
							b2.setEnabled(false);visit[0]=1;
							done[1]=1;return 0;
						}
						if(done[2]==0)
						{
							b3.setText("O");
							b3.setEnabled(false);visit[0]=1;
							done[2]=1;
							return 0;
						}	
					}
		dan=0;
		if(done[3]==1)
			dan++;
			if(done[4]==1)
				dan++;
				if(done[5]==1)
					dan++;
					if(dan==2&&visit[1]==0)
					{
						if(done[3]==0)
						{
							b4.setText("O");
							b4.setEnabled(false);visit[1]=1;
							done[3]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");
							b5.setEnabled(false);visit[1]=1;
							done[4]=1;return 0;
						}
						if(done[5]==0)
						{
							b6.setText("O");
							b6.setEnabled(false);visit[1]=1;
							done[5]=1;return 0;
						}	
					}
		dan=0;
		if(done[6]==1)
			dan++;
			if(done[7]==1)
				dan++;
				if(done[8]==1)
					dan++;
					if(dan==2&&visit[2]==0)
					{
						if(done[6]==0)
						{
							b7.setText("O");
							b7.setEnabled(false);visit[2]=1;
							done[6]=1;return 0;
						}
						else if(done[7]==0)
						{
							b8.setText("O");
							b8.setEnabled(false);visit[2]=1;
							done[7]=1;return 0;
						}
						else if(done[8]==0)
						{
							b9.setText("O");
							b9.setEnabled(false);visit[2]=1;
							done[8]=1;return 0;
						}	
					}
		dan=0;
		if(done[0]==1)
			dan++;
			if(done[4]==1)
				dan++;
				if(done[8]==1)
					dan++;
					if(dan==2&&visit[3]==0)
					{
						if(done[0]==0)
						{
							b1.setText("O");
							b1.setEnabled(false);visit[3]=1;
							done[0]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");
							b5.setEnabled(false);visit[3]=1;
							done[4]=1;return 0;
						}
						if(done[8]==0)
						{
							b9.setText("O");
							b9.setEnabled(false);visit[3]=1;
							done[8]=1;return 0;
						}
					}
		dan=0;
		if(done[6]==1)
			dan++;
			if(done[4]==1)
				dan++;
				if(done[2]==1)
					dan++;
					if(dan==2&&visit[4]==0)
					{
						if(done[6]==0)
						{
							b7.setText("O");
							b7.setEnabled(false);visit[4]=1;
							done[6]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");b5.setEnabled(false);visit[4]=1;
							done[4]=1;return 0;
						}
						if(done[2]==0)
						{
							b3.setText("O");b3.setEnabled(false);visit[4]=1;
							done[2]=1;return 0;
						}
					}
		dan=0;
		if(done[0]==1)
			dan++;
			if(done[3]==1)
				dan++;
				if(done[6]==1)
					dan++;
					if(dan==2&&visit[5]==0)
					{
						if(done[6]==0)
						{
							b7.setText("O");b7.setEnabled(false);visit[5]=1;
							done[6]=1;return 0;
						}
						if(done[0]==0)
						{
							b1.setText("O");b1.setEnabled(false);visit[5]=1;
							done[0]=1;return 0;
						}
						if(done[3]==0)
						{
							b4.setText("O");b4.setEnabled(false);visit[5]=1;
							done[3]=1;return 0;
						}
					}
		dan=0;
		if(done[1]==1)
			dan++;
			if(done[4]==1)
				dan++;
				if(done[7]==1)
					dan++;
					if(dan==2&&visit[6]==0)
					{
						if(done[1]==0)
						{
							b2.setText("O");b2.setEnabled(false);visit[6]=1;
							done[1]=1;return 0;
						}
						if(done[7]==0)
						{
							b8.setText("O");b8.setEnabled(false);visit[6]=1;
							done[7]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");b5.setEnabled(false);visit[6]=1;
							done[4]=1;return 0;
						}
					}
		dan=0;
		if(done[2]==1)
			dan++;
			if(done[5]==1)
				dan++;
				if(done[8]==1)
					dan++;
					if(dan==2&&visit[7]==0)
					{
						if(done[2]==0)
						{
							b3.setText("O");b3.setEnabled(false);visit[7]=1;
							done[2]=1;return 0;
						}
						if(done[5]==0)
						{
							b6.setText("O");b6.setEnabled(false);visit[7]=1;
							done[5]=1;return 0;
						}
						if(done[8]==0)
						{
							b9.setText("O");b9.setEnabled(false);visit[7]=1;
							done[8]=1;return 0;
						}
					}
		dan=0;
		if(done[0]==-1)
			dan++;
			if(done[1]==-1)
				dan++;
				if(done[2]==-1)
					dan++;
					if(dan==2&&visit[8]==0)
					{
						if(done[0]==0)
						{
							b1.setText("O");
							b1.setEnabled(false);visit[8]=1;
							done[0]=1;
							return 0;
						}
						if(done[1]==0)
						{
							b2.setText("O");
							b2.setEnabled(false);visit[8]=1;
							done[1]=1;return 0;
						}
						if(done[2]==0)
						{
							b3.setText("O");
							b3.setEnabled(false);visit[8]=1;
							done[2]=1;
							return 0;
						}	
					}
		dan=0;
		if(done[3]==-1)
			dan++;
			if(done[4]==-1)
				dan++;
				if(done[5]==-1)
					dan++;
					if(dan==2&&visit[9]==0)
					{
						if(done[3]==0)
						{
							b4.setText("O");
							b4.setEnabled(false);visit[9]=1;
							done[3]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");
							b5.setEnabled(false);visit[9]=1;
							done[4]=1;return 0;
						}
						if(done[5]==0)
						{
							b6.setText("O");
							b6.setEnabled(false);visit[9]=1;
							done[5]=1;return 0;
						}	
					}
		dan=0;
		if(done[6]==-1)
			dan++;
			if(done[7]==-1)
				dan++;
				if(done[8]==-1)
					dan++;
					if(dan==2&&visit[10]==0)
					{
						if(done[6]==0)
						{
							b7.setText("O");
							b7.setEnabled(false);visit[10]=1;
							done[6]=1;return 0;
						}
						else if(done[7]==0)
						{
							b8.setText("O");
							b8.setEnabled(false);visit[10]=1;
							done[7]=1;return 0;
						}
						else if(done[8]==0)
						{
							b9.setText("O");
							b9.setEnabled(false);visit[10]=1;
							done[8]=1;return 0;
						}	
					}
		dan=0;
		if(done[0]==-1)
			dan++;
			if(done[4]==-1)
				dan++;
				if(done[8]==-1)
					dan++;
					if(dan==2&&visit[11]==0)
					{
						if(done[0]==0)
						{
							b1.setText("O");
							b1.setEnabled(false);visit[11]=1;
							done[0]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");
							b5.setEnabled(false);visit[11]=1;
							done[4]=1;return 0;
						}
						if(done[8]==0)
						{
							b9.setText("O");
							b9.setEnabled(false);visit[11]=1;
							done[8]=1;return 0;
						}
					}
		dan=0;
		if(done[6]==-1)
			dan++;
			if(done[4]==-1)
				dan++;
				if(done[2]==-1)
					dan++;
					if(dan==2&&visit[12]==0)
					{
						if(done[6]==0)
						{
							b7.setText("O");
							b7.setEnabled(false);visit[12]=1;
							done[6]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");b5.setEnabled(false);visit[12]=1;
							done[4]=1;return 0;
						}
						if(done[2]==0)
						{
							b3.setText("O");b3.setEnabled(false);visit[12]=1;
							done[2]=1;return 0;
						}
					}
		dan=0;
		if(done[0]==-1)
			dan++;
			if(done[3]==-1)
				dan++;
				if(done[6]==-1)
					dan++;
					if(dan==2&&visit[13]==0)
					{
						if(done[6]==0)
						{
							b7.setText("O");b7.setEnabled(false);visit[13]=1;
							done[6]=1;return 0;
						}
						if(done[0]==0)
						{
							b1.setText("O");b1.setEnabled(false);visit[13]=1;
							done[0]=1;return 0;
						}
						if(done[3]==0)
						{
							b4.setText("O");b4.setEnabled(false);visit[13]=1;
							done[3]=1;return 0;
						}
					}
		dan=0;
		if(done[1]==-1)
			dan++;
			if(done[4]==-1)
				dan++;
				if(done[7]==-1)
					dan++;
					if(dan==2&&visit[14]==0)
					{
						if(done[1]==0)
						{
							b2.setText("O");b2.setEnabled(false);visit[14]=1;
							done[1]=1;return 0;
						}
						if(done[7]==0)
						{
							b8.setText("O");b8.setEnabled(false);visit[14]=1;
							done[7]=1;return 0;
						}
						if(done[4]==0)
						{
							b5.setText("O");b5.setEnabled(false);visit[14]=1;
							done[4]=1;return 0;
						}
					}
		dan=0;
		if(done[2]==-1)
			dan++;
			if(done[5]==-1)
				dan++;
				if(done[8]==-1)
					dan++;
					if(dan==2&&visit[15]==0)
					{
						if(done[2]==0)
						{
							b3.setText("O");b3.setEnabled(false);visit[15]=1;
							done[2]=1;return 0;
						}
						if(done[5]==0)
						{
							b6.setText("O");b6.setEnabled(false);visit[15]=1;
							done[5]=1;return 0;
						}
						if(done[8]==0)
						{
							b9.setText("O");b9.setEnabled(false);visit[15]=1;
							done[8]=1;return 0;
						}
					}
		if(done[4]==-1&&visit[16]==0)
		{
			b3.setText("O");
			b3.setEnabled(false);
			done[2]=1;
			visit[16]=1;
			return 0;
		}
		if((done[2]==-1&&done[6]==-1||done[0]==-1&&done[8]==-1)&&visit[17]==0)
		{
			b2.setText("O");
			b2.setEnabled(false);
			done[1]=1;
			visit[17]=1;
			return 0;
		}
		if(done[0]==0)
		{
			b1.setText("O");
			b1.setEnabled(false);
			done[0]=1;
			return 0;
		}
		else if(done[8]==0)
		{
			b9.setText("O");
			b9.setEnabled(false);
			done[8]=1;
			return 0;
		}
		else if(done[2]==0)
		{
			b3.setText("O");
			b3.setEnabled(false);
			done[2]=1;
			return 0;
		}
		else if(done[6]==0)
		{
			b7.setText("O");
			b7.setEnabled(false);
			done[6]=1;
			return 0;
		}
		else if(done[1]==0)
		{
			b2.setText("O");
			b2.setEnabled(false);
			done[1]=1;
			return 0;
		}
		else if(done[5]==0)
		{
			b6.setText("O");
			b6.setEnabled(false);
			done[5]=1;
			return 0;
		}
		else if(done[3]==0)
		{
			b4.setText("O");
			b4.setEnabled(false);
			done[3]=1;
			return 0;
		}
		else if(done[7]==0)
		{
			b8.setText("O");
			b8.setEnabled(false);
			done[7]=1;
			return 0;
		}
		}
		return 0;	
	}	
}
public class Tic_Tac_Toe
{
	public static void main(String[] args)
	{
		TTT gameframe = new TTT("Tic Tac Toe");
		Thread game = new Thread(gameframe);
		game.start();
	}
}
