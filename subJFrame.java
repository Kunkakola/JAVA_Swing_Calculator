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
		//调用JFrame类中方法关闭框架
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
