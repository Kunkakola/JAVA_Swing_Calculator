package chapter11;
import java.awt.*;
import javax.swing.*;

public class subJFrame extends JFrame{

	public subJFrame() {
		// TODO Auto-generated constructor stub
	}
	public subJFrame(String title) {
		// TODO Auto-generated constructor stub
		super(title);
	}
	protected void frameInit(){
		super.frameInit();
		//����JFrame���з����رտ��
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
