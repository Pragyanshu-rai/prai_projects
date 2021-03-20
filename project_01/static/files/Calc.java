// Simple Calculator Application

//package calculator;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Calci extends JFrame implements ActionListener
{
	int set=1,af=0,mf=0,df=0,mof=0,sf=0,pf=0,opf=0;
	float a=0,b=0;
	String mem="Empty",icmem="Empty";
	JPanel upper = new JPanel(),lower = new JPanel(),exitdia = new JPanel();
	JMenuBar menubar = new JMenuBar();
	JMenu view = new JMenu("View"),edit=new JMenu("Edit"),help=new JMenu("Help"),more=new JMenu("More...");
	JMenuItem menuitem;
	JRadioButtonMenuItem rb = new JRadioButtonMenuItem("Radio");
	JCheckBoxMenuItem cb = new JCheckBoxMenuItem("Check Box");
	JButton b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bdot,add,sub,mul,div,mod,oneby,clear,ca,back,equal,pow,fact;
	JTextField show = new JTextField("0");
	public Calci(String s)
	{
		super(s);
		menubar.add(view);
		menubar.add(edit);
		menubar.add(help);
		b0=new JButton("0");
		b1=new JButton("1");
		b2=new JButton("2");
		b3=new JButton("3");
		b4=new JButton("4");
		b5=new JButton("5");
		b6=new JButton("6");
		b7=new JButton("7");
		b8=new JButton("8");
		b9=new JButton("9");
		add=new JButton("+");
		sub=new JButton("-");
		div=new JButton("/");
		mul=new JButton("*");
		mod=new JButton("%");
		clear=new JButton("C");
		ca=new JButton("CE");
		back=new JButton("<-");
		oneby=new JButton("1/x");
		bdot=new JButton(".");
		pow=new JButton("^");
		fact=new JButton("!");
		equal=new JButton("=");
	}
	public void crmen()
	{
		menuitem = new JMenuItem("Item1");
		view.add(menuitem);
		menuitem = new JMenuItem("Item2");
		view.add(menuitem);
		view.addSeparator();
		view.add(rb);
		view.addSeparator();
		view.add(cb);
		view.addSeparator();
		menuitem = new JMenuItem("More Item");
		more.add(menuitem);
		more.addSeparator();
		menuitem = new JMenuItem("More Item again");
		more.add(menuitem);
		view.add(more);
		menubar.add(view);
		menubar.add(edit);
		menubar.add(help);
		menubar.setVisible(true);
	}
	public void create()
	{
		crmen();
		show.setBounds(10,20,280,50);
		show.setHorizontalAlignment(show.RIGHT);// to set the text Placement in the text field
		show.setCaretPosition(1);//to set the cursor Position
		show.setFont(new Font("Calibri",Font.BOLD,25));//best font
		show.setCaretColor(Color.WHITE);//to make the cusor invisible
		lower.setBounds(10,75,280,260);
		back.setBounds(0,0,52,48);
		back.setFocusPainted(false);
		ca.setBounds(57,0,52,48);
		ca.setFocusPainted(false);	
		clear.setBounds(114,0,52,48);
		clear.setFocusPainted(false);
		pow.setBounds(171,0,52,48);
		pow.setFocusPainted(false);
		fact.setBounds(228,0,52,48);
		fact.setFocusPainted(false);
		b7.setBounds(0,53,52,48);
		b7.setFocusPainted(false);
		b8.setBounds(57,53,52,48);
		b8.setFocusPainted(false);
		b9.setBounds(114,53,52,48);
		b9.setFocusPainted(false);
		div.setBounds(171,53,52,48);
		div.setFocusPainted(false);
		mod.setBounds(228,53,52,48);
		mod.setFocusPainted(false);
		b4.setBounds(0,106,52,48);
		b4.setFocusPainted(false);
		b5.setBounds(57,106,52,48);
		b5.setFocusPainted(false);
		b6.setBounds(114,106,52,48);
		b6.setFocusPainted(false);
		mul.setBounds(171,106,52,48);
		mul.setFocusPainted(false);
		oneby.setBounds(228,106,52,48);
		oneby.setFocusPainted(false);
		oneby.setFont(new Font(Font.SERIF,Font.PLAIN,11));
		b1.setBounds(0,159,52,48);
		b1.setFocusPainted(false);//to remove the border from the text
		b2.setBounds(57,159,52,48);
		b2.setFocusPainted(false);
		b3.setBounds(114,159,52,48);
		b3.setFocusPainted(false);
		sub.setBounds(171,159,52,48);
		sub.setFocusPainted(false);
		equal.setBounds(228,159,52,101);
		equal.setFocusPainted(false);
		equal.setFont(new Font(Font.SERIF,Font.BOLD,15));
		b0.setBounds(0,212,109,48);
		b0.setFocusPainted(false);
		bdot.setBounds(114,212,52,48);
		bdot.setFont(new Font(Font.SERIF,Font.BOLD,16));
		bdot.setFocusPainted(false);
		add.setBounds(171,212,52,48);
		add.setFocusPainted(false);
		lower.setLayout(null);
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		oneby.addActionListener(this);
		add.addActionListener(this);
		sub.addActionListener(this);
		mul.addActionListener(this);
		div.addActionListener(this);
		mod.addActionListener(this);
		back.addActionListener(this);
		equal.addActionListener(this);
		clear.addActionListener(this);
		ca.addActionListener(this);
		pow.addActionListener(this);
		fact.addActionListener(this);
		bdot.addActionListener(this);
		lower.add(back);
		lower.add(ca);
		lower.add(clear);
		lower.add(pow);
		lower.add(fact);
		lower.add(b7);
		lower.add(b8);
		lower.add(b9);
		lower.add(div);
		lower.add(mod);
		lower.add(b4);
		lower.add(b5);
		lower.add(b6);
		lower.add(mul);
		lower.add(oneby);
		lower.add(b1);
		lower.add(b2);
		lower.add(b3);
		lower.add(sub);
		lower.add(equal);
		lower.add(b0);
		lower.add(bdot);
		lower.add(add);
		setJMenuBar(menubar);
		add(show);
		add(lower);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==b0)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"0");
			icmem=show.getText();
		}
		else if(ae.getSource()==b1)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"1");
			icmem=show.getText();
		}
		else if(ae.getSource()==b2)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"2");
			icmem=show.getText();
		}
		else if(ae.getSource()==b3)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"3");
			icmem=show.getText();
		}
		else if(ae.getSource()==b4)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"4");
			icmem=show.getText();
		}
		else if(ae.getSource()==b5)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"5");
			icmem=show.getText();
		}
		else if(ae.getSource()==b6)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"6");
			icmem=show.getText();
		}
		else if(ae.getSource()==b7)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"7");
			icmem=show.getText();
		}
		else if(ae.getSource()==b8)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"8");
			icmem=show.getText();
		}
		else if(ae.getSource()==b9)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+"9");
			icmem=show.getText();
		}
		else if(ae.getSource()==add)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				icmem=show.getText();
				show.setText(null);
				af+=1;
				opf=1;
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
				opf=af=0;
			}				
		}
		else if(ae.getSource()==sub)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				icmem=show.getText();
				show.setText(null);
				sf+=1;
				opf=1;
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
				opf=sf=0;
			}
		}
		else if(ae.getSource()==div)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				icmem=show.getText();
				show.setText(null);
				df+=1;
				opf=1;
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
				opf=df=0;
			}
		}
		else if(ae.getSource()==mul)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				icmem=show.getText();
				show.setText(null);
				mf+=1;
				opf=1;
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
				opf=mf=0;				
			}			
		}
		else if(ae.getSource()==mod)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				icmem=show.getText();
				show.setText(null);
				mof+=1;
				opf=1;
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
				opf=mof=0;
			}
		}
		else if(ae.getSource()==bdot)
		{
			if(opf==0)
			{
				show.setText(null);
				opf=1;
			}
			show.setText(show.getText()+".");
			icmem=show.getText();
		}
		else if(ae.getSource()==pow)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				icmem=show.getText();
				show.setText(null);
				pf+=1;
				opf=1;
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
				opf=pf=0;
			}
		}
		else if(ae.getSource()==fact)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				a=fac(a);
				show.setText(Float.toString(a));
				mem=show.getText();
				opf=0;
			}
			catch(NumberFormatException nfe)
			{
				show.setText("ERROR!!!");
				opf=0;
			}	
		}
		else if(ae.getSource()==back)
		{
			show.setText(null);
			show.setText(icmem);
		}
		else if(ae.getSource()==clear)
		{
			show.setText(null);
			af=sf=df=mf=mof=pf=opf=0;
		}
		else if(ae.getSource()==ca)
		{
			show.setText(null);
			show.setText(mem);
			af=sf=df=mf=mof=pf=opf=0;
		}
		else if(ae.getSource()==oneby)
		{
			try
			{
				a=Float.parseFloat(show.getText());
				show.setText(Float.toString(1/a));
				mem=show.getText();
			}
			catch(Exception nfe)
			{
				show.setText("ERROR!!!");
			}
		}
		else if(ae.getSource()==equal)
		{
			if(af==1)
			{
				opf=af=0;
				try
				{
					b=Float.parseFloat(show.getText());
					String ss=Float.toString(a+b);
					show.setText(ss);
					mem=show.getText();
					b=0;
				}
				catch(Exception nfe)
				{
					show.setText("ERROR!!!");
				}
				
			}
			else if(sf==1)
			{
				opf=sf=0;
				try
				{
					b=Float.parseFloat(show.getText());
					String ss=Float.toString(a-b);
					show.setText(ss);
					mem=show.getText();
					b=0;
				}
				catch(Exception nfe)
				{
					show.setText("ERROR!!!");
				}
			}
			else if(mf==1)
			{
				opf=mf=0;
				try
				{
					b=Float.parseFloat(show.getText());
					String ss=Float.toString(a*b);
					show.setText(ss);
					mem=show.getText();
					b=0;
				}
				catch(Exception nfe)
				{
					show.setText("ERROR!!!");
				}
			}
			else if(df==1)
			{
				opf=df=0;				
				try
				{
					b=Float.parseFloat(show.getText());
					String ss=Float.toString(a/b);
					show.setText(ss);
					mem=show.getText();
					b=0;
				}
				catch(Exception nfe)
				{
					show.setText("ERROR!!!");
				}
			}
			else if(mof==1)
			{
				opf=mof=0;
				try
				{
					b=Float.parseFloat(show.getText());
					String ss=Float.toString(a%b);
					show.setText(ss);
					mem=show.getText();
					b=0;
				}
				catch(Exception nfe)
				{
					show.setText("ERROR!!!");
				}
			}
			else if(pf==1)
			{
				opf=pf=0;
				try
				{
					b=Float.parseFloat(show.getText());
					String ss=Float.toString(powr(a,b));
					show.setText(ss);
					mem=show.getText();
					b=0;
				}
				catch(Exception nfe)
				{
					show.setText("ERROR!!!");
				}				
			}
			else
				show.setText(show.getText());
		}
	}
	public float fac(float f)
	{
		float x=1;
		if(f==0)
			return 1;
		else
			for(int i=1;i<=f;i++)
				x=x*i;
		return x;
	}
	public float powr(float ba,float p)
	{
		float c=ba;
		if(ba==0&&p==0)
			return 1;
		else if(ba==0)
			return 0;
		else if(p==0)
			return 1;
		else
			for(int i=1;i<p;i++)
				ba=c*ba;
		return ba;
	}
}
public class Calc
{
	public static void main(String[] x)
	{
		Calci ca = new Calci("Calculator");
		ca.setResizable(false);
		ca.setBounds(500,80,300,400);
		ca.setLayout(null);
		ca.create();
		ca.setDefaultCloseOperation(Calci.EXIT_ON_CLOSE);
	}	
}
