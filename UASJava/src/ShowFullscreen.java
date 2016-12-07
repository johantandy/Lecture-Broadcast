import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class ShowFullscreen {
	static BufferedImage bi;
	public static void main(String[] args,int i) {
		// TODO Auto-generated method stub
		JFrame theGUI = new JFrame();
		theGUI.setLayout(null);
		JLabel pic = new JLabel();
		pic.setBounds(20, -10, 1366,768);
		theGUI.setLayout(null);
		theGUI.setTitle("TestApp");
		theGUI.setSize(1280, 720);
		//theGUI.getContentPane().add(pic);
		theGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//theGUI.setUndecorated(true);
		theGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		theGUI.setVisible(true);
		theGUI.add(pic);
		
		Timer timer =new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if(i==1){
				 bi=Server1.retImage();}
				if(i==2){
					 bi=Server1.retImage1();	
				}
				BufferedImage resize = Scalr.resize(bi,Mode.AUTOMATIC,1320,714);
				pic.setIcon(new ImageIcon(resize));
			}
		}, 1,1);
		
		
	}

}
