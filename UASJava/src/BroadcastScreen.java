import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BroadcastScreen {
	static JFrame theGUI = new JFrame();
	static JLabel pic = new JLabel();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		startP();

	}
	
	public static void startP() {
		theGUI.setLayout(null);
		theGUI.setTitle("TestApp");
		theGUI.setSize(1280, 720);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theGUI.setVisible(true);
		theGUI.add(pic);
		try {
			JOptionPane.showMessageDialog(null, "run server");
			ServerSocket listen = new ServerSocket(9093);
			Socket s1 = listen.accept();
			Thread t = new ServersP(s1);
			t.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setLbl(BufferedImage bi) {
		// pic.setIcon(null);
		pic.setIcon(new ImageIcon(bi));


}
}

class ServersP extends Thread {
	private Socket socket = new Socket();
	private String client;
	int i = 0;

	ServersP(Socket s) {
		socket = s;
		client = s.getRemoteSocketAddress().toString();
		System.out.println("Incoming Image from " + client);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {

				BufferedImage img = ImageIO.read(ImageIO.createImageInputStream(socket.getInputStream()));
				BroadcastScreen.setLbl(img);

				/*File myFile = new File("image.png");
				byte[] mybytearray = new byte[(int) myFile.length()];
			      BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
			      bis.read(mybytearray, 0, mybytearray.length);
			      OutputStream os = socket.getOutputStream();
			      os.write(mybytearray, 0, mybytearray.length);
			      os.flush();
			      BroadcastScreen.setLbl();*/
			      
			}
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("close Connection from " + client);
		}

	}

}
