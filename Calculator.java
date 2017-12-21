package chapter11;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.*;

public class Calculator{
	//两个操作数
	String str1="",str2="";
	//操作符
	String signal="";
	//key_point标志是否已输入小数点，key_signal标志是否已输入计算符
	boolean key_point=false,key_signal=false;
	//上一个按下的键是不是一般运算符
	boolean last_key_is_signal=false;
    //判断是否是显示的答案的标志，为0代表不是算术结果，为-1表示是报错信息，为1表示是算术结果
	int key_result=0;
	
	JFrame frame;
	Container contentPane;
	JPanel head,body;
	JTextField field;
	String numLabels[]= {"sin","1/x","x^2","<-",
						 "7","8","9","+",
						 "4","5","6","-",
						 "1","2","3","*",
						 "0",".","=","/"
						 };
	JButton buttons[]=new JButton[numLabels.length];
	
	
	public Calculator(){
		frame=new subJFrame("Calculator");
		frame.setLocation(300, 300);
		contentPane=frame.getContentPane();
		//设置主界面为BorderLayout
		contentPane.setLayout(new BorderLayout(5,5));
		
		CalEventHandler handler=new CalEventHandler();
		//显示部分
		head=new JPanel();
		field=new JTextField(20);
		//设置文本框为不可编辑状态
		field.setEditable(false);
		//设置文本框为右对齐方式
		field.setHorizontalAlignment(JTextField.RIGHT);
		head.add(field);
		contentPane.add(BorderLayout.NORTH,head);
		
		//按键部分
		body=new JPanel(new GridLayout(5,4,5,5));
		for(int i=0;i<numLabels.length;i++) {
			buttons[i]=new JButton(numLabels[i]);
			//每个按钮添加监听对象
			buttons[i].addActionListener(handler);
			body.add(buttons[i]);
		}
		contentPane.add(BorderLayout.CENTER,body);
		
		//必须放在最后否则无显示
		frame.setSize(300,300);
		frame.setVisible(true);
	}
	
	void calculate() {
		Double num1=Double.parseDouble(str1);
		Double num2=Double.parseDouble(str2);
		String result="";
		
		switch(signal) {
			case "+":
				result=Double.toString(num1+num2);
				break;
			case "-":
				result=Double.toString(num1-num2);
				break;
			case "*":
				result=Double.toString(num1*num2);
				break;
			case "/":
				if(num2==0.0) {
					field.setText("除数不能为0！");
					key_result=-1;
				}
				else result=Double.toString(num1/num2);
				break;
			default:
				result="0.0";
				break;
		}
		
		field.setText(result);
	}
	
	//使用内隐类处理按键响应
	private class CalEventHandler implements ActionListener{
		
		//实现接口
		public void actionPerformed(ActionEvent e) {
			String str=field.getText();
			
			if(e.getActionCommand().equals("0")||e.getActionCommand().equals("1")||
			   e.getActionCommand().equals("2")||e.getActionCommand().equals("3")||
			   e.getActionCommand().equals("4")||e.getActionCommand().equals("5")||
			   e.getActionCommand().equals("6")||e.getActionCommand().equals("7")||
			   e.getActionCommand().equals("8")||e.getActionCommand().equals("9")) {
				//不是算术结果或者报错信息的时候
				if (key_result==0) {
					str+=e.getActionCommand();
					field.setText(str);
				}
				else {
					str=e.getActionCommand();
					field.setText(str);
				}
				last_key_is_signal=false;
				key_result=0;
			}
			//处理回退
			else if (e.getActionCommand().equals("<-")) {
				if(key_result!=0)	field.setText("");
				
				else if (str.length()>0) {
					switch(str.charAt(str.length()-1)){
					case '+':
						signal="";
						key_signal=false;
						last_key_is_signal=false;
						key_result=0;
						break;
					case '-':
						signal="";
						key_signal=false;
						last_key_is_signal=false;
						key_result=0;
						break;
					case '*':
						signal="";
						key_signal=false;
						last_key_is_signal=false;
						key_result=0;
						break;
					case '/':
						signal="";
						key_signal=false;
						last_key_is_signal=false;
						key_result=0;
						break;
					case '.':
						key_point=false;
						key_result=0;
						break;
					}
					str=str.substring(0,str.length()-1);
					field.setText(str);
					if(str.length()>0) {
						switch(str.charAt(str.length()-1)){
							case '+':
								last_key_is_signal=true;
								key_result=0;
								break;
							case '-':
								last_key_is_signal=true;
								key_result=0;
								break;
							case '*':
								last_key_is_signal=true;
								key_result=0;
								break;
							case '/':
								last_key_is_signal=true;
								key_result=0;
								break;
							default:
								last_key_is_signal=false;
								key_result=0;
								break;
						}
					}
					
				}	
			}
			else if (e.getActionCommand().equals(".")) {
				if(!key_point) {
					str=str+".";
					field.setText(str);
					key_point=true;
					last_key_is_signal=false;
					key_result=0;
				}
				
			}
			else if (e.getActionCommand().equals("+")) {
				//两个一般运算符不能相邻出现
				if(!last_key_is_signal) {
					if(key_signal) {
						str2=str.substring(str1.length()+1);
						calculate();
						str=field.getText();
					}
					str1=str;
					str+="+";
					signal="+";
					field.setText(str);
					key_signal=true;
					last_key_is_signal=true;
					key_result=0;
				}
			}
			
			else if (e.getActionCommand().equals("-")) {
				//两个一般运算符不能相邻出现
				if(!last_key_is_signal) {
					if(key_signal) {
						str2=str.substring(str1.length()+1);
						calculate();
						str=field.getText();
					}
					str1=str;
					str+="-";
					signal="-";
					field.setText(str);
					key_signal=true;
					last_key_is_signal=true;
					key_result=0;
				}
			}
			
			else if (e.getActionCommand().equals("*")) {
				//两个一般运算符不能相邻出现
				if(!last_key_is_signal) {
					if(key_signal) {
						str2=str.substring(str1.length()+1);
						calculate();
						str=field.getText();
					}
					str1=str;
					str+="*";
					signal="*";
					field.setText(str);
					key_signal=true;
					last_key_is_signal=true;
					key_result=0;
				}
			}
			
			else if (e.getActionCommand().equals("/")) {
				//两个一般运算符不能相邻出现
				if(!last_key_is_signal) {
					if(key_signal) {
						str2=str.substring(str1.length()+1);
						calculate();
						str=field.getText();
					}
					str1=str;
					str+="/";
					signal="/";
					field.setText(str);
					key_signal=true;
					last_key_is_signal=true;
					key_result=0;
				}
			}
			
			else if (e.getActionCommand().equals("=")) {
				if(key_signal) {
					if(str.length()>str1.length()+1) {    
						str2=str.substring(str1.length()+1);
					}
					else {
						str2="0.0";
					}
					calculate();
					signal="";
					key_point=false;
					last_key_is_signal=false;
					key_signal=false;
					key_result=1;
				}
				signal="";
				key_point=false;
				last_key_is_signal=false;
				key_signal=false;
				key_result=1;
			}
			
			else if(e.getActionCommand().equals("sin")) {
				if(str.length()!=0&&!key_signal) {
					Double num=Double.parseDouble(str);
					String ret=Double.toString(Math.sin(num));
					field.setText(ret);
					key_result=1;
				}
				else {
					field.setText("输入有误！");
					key_result=-1;
				}
			}
			
			else if(e.getActionCommand().equals("1/x")) {
				if(str.length()!=0&&!key_signal) {
					Double num=Double.parseDouble(str);
					String ret=Double.toString(1.0/num);
					field.setText(ret);
					key_result=1;
				}
				else {
					field.setText("输入有误！");
					key_result=-1;
				}
			}
			
			else if(e.getActionCommand().equals("x^2")) {
				if(str.length()!=0&&!key_signal) {
					Double num=Double.parseDouble(str);
					String ret=Double.toString(num*num);
					field.setText(ret);
					key_result=1;
				}
				else {
					field.setText("输入有误！");
					key_result=-1;
				}
			}
			
		}
	}
	
	
	public static void main(String args[]) {
		Calculator obj=new Calculator();
	}
}
