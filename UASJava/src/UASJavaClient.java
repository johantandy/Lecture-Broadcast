import java.awt.AWTException;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.xml.bind.Marshaller.Listener;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class UASJavaClient {
	static JFrame theGUI = new JFrame();
	static JLabel pic = new JLabel();
	static String answerP;
	static boolean started = false;
	static Socket s1;
	static PrintWriter out;
	static Socket s;
	static Boolean isOpen=false;
	static Boolean isOpen1=false;
	static Boolean isOpenMain=false;
	static String server1;
	static String server;
	static String answer;
	static String title;
	static int ports;
	static Rectangle screenact;
	static Timer timer;
	static Robot robot = null;
	public static void sambilJalan() throws UnknownHostException, IOException, AWTException{
		Robot robot = new Robot();
		Rectangle screenact = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		s = new Socket(server1, ports);
		BufferedImage capture = robot.createScreenCapture(screenact);
		ImageIO.write(capture, "png", s.getOutputStream());
	}
	

	public static void main(String[] args) {
		Timer timer = new Timer();
		
		try {
			robot = new Robot();
		} catch (AWTException e2) {
			// TODO Auto-generated catch block
			//e2.printStackTrace();
		}
		if(isOpenMain!=true){
		answer = null;
		server = JOptionPane.showInputDialog("Enter Server IP");
		isOpenMain=true;
		
		
		server1=server;
		// Get Screen Size
		
		screenact = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		// Request Port from server
		try {
			
			/*
			Socket s1 = new Socket(server, 8998);
			BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			answerP = in1.readLine();
			System.out.println(answerP);
			JOptionPane.showMessageDialog(null, answerP);
			s1.close();*/
			
			s1=new Socket(server, 8999);
			//BufferedReader cin= new BufferedReader(new InputStreamReader("PORT"));
			out = new PrintWriter(s1.getOutputStream(),true);
			out.println("port");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
			answer = in.readLine();
			out.println("title");
			title=in.readLine();
			s1.close();
			
		} catch (Exception ez) {
			//JOptionPane.showMessageDialog(null, "1 "+ez.toString());
		}

		int port = Integer.parseInt(answer);
		ports=port;
		//JOptionPane.showMessageDialog(null, port);
		answerP = "0";}
		//startP();
		// Timer Start for loop take Screenshot & send to server through socket
		
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					try {
					/*	Socket s1 = new Socket(server, 8998);
						BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
						answerP = in1.readLine();
						System.out.println(answerP);
						if (answerP.equals('0')) {
							started = false;
						}
						if (answerP.equals('1') && started == false) {
							startP();
						}
						//s1.close();
*/					} catch (Exception ez) {
						JOptionPane.showMessageDialog(null, "conn stat" + ez.toString());
					}
					
					//Socket s2=new Socket(server, 8999);
					s = new Socket(server, ports);
					//s1=new Socket(server, 8999);
					BufferedImage capture = robot.createScreenCapture(screenact);
					ImageIO.write(capture, "png", s.getOutputStream());
					s1=new Socket(server, 8999);
					//BufferedReader cin= new BufferedReader(new InputStreamReader("PORT"));
					out = new PrintWriter(s1.getOutputStream(),true);
					out.println("stat");
					BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
					String reply = in.readLine();
					s1.close();
					System.out.println(reply);
					if(reply.equals("1")&&isOpen==false){
						theGUI.setVisible(true);
						//JOptionPane.showMessageDialog(null, "run1");
					startP();
					isOpen=true;
					isOpen1=true;
					}
					if(reply.equals("0")&&isOpen1==true){
						isOpen=false;
						isOpen1=false;
						theGUI.setVisible(false);
						//JOptionPane.showMessageDialog(null, "run");
						//timer.cancel();
						//main(null);
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					//JOptionPane.showMessageDialog(null, String.valueOf("2" +e1.toString()));
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				}
			}
		}, 1, 2);

	}
	
	public static void startNormal() throws AWTException{
		Timer timer = new Timer();
		Robot robot = new Robot();
		Rectangle screenact = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
		// Timer Start for loop take Screenshot & send to server through socket
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							try {
							/*	Socket s1 = new Socket(server, 8998);
								BufferedReader in1 = new BufferedReader(new InputStreamReader(s1.getInputStream()));
								answerP = in1.readLine();
								System.out.println(answerP);
								if (answerP.equals('0')) {
									started = false;
								}
								if (answerP.equals('1') && started == false) {
									startP();
								}
								//s1.close();
		*/					} catch (Exception ez) {
								JOptionPane.showMessageDialog(null, "conn stat" + ez.toString());
							}
							
							//Socket s2=new Socket(server, 8999);
							s = new Socket(server1, ports);
							//s1=new Socket(server, 8999);
							BufferedImage capture = robot.createScreenCapture(screenact);
							ImageIO.write(capture, "png", s.getOutputStream());
							s1=new Socket(server1, 8999);
							//BufferedReader cin= new BufferedReader(new InputStreamReader("PORT"));
							out = new PrintWriter(s1.getOutputStream(),true);
							out.println("stat");
							BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
							String reply = in.readLine();
							s1.close();
							System.out.println(reply);
							if(reply.equals("1")&&isOpen==false){
								theGUI.setVisible(true);
								JOptionPane.showMessageDialog(null, "run");
								isOpen=true;
								isOpen1=true;
							startP();
							
							}
							if(reply.equals("0")&&isOpen1==true){
								isOpen=false;
								isOpen1=false;
								theGUI.setVisible(false);
								main(null);
								
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							//JOptionPane.showMessageDialog(null, String.valueOf("2" +e1.toString()));
						} catch (AWTException e) {
							// TODO Auto-generated catch block
							//e.printStackTrace();
						}
					}
				}, 1, 2);
	}

	// Start Lecture Broadcast method

	public static void startP() throws AWTException {
		started = true;
		pic.setBounds(20, -10, 1366,768);
		theGUI.setLayout(null);
		theGUI.setTitle(title);
		theGUI.setSize(1280, 720);
		//theGUI.getContentPane().add(pic);
		theGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//theGUI.setUndecorated(true);
		theGUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		theGUI.setVisible(true);
		theGUI.add(pic);
		try {ServerSocket listen = new ServerSocket(8998);
			while(true){
			//JOptionPane.showMessageDialog(null, "run server");
				s1=new Socket(server1, 8999);
				//BufferedReader cin= new BufferedReader(new InputStreamReader("PORT"));
				out = new PrintWriter(s1.getOutputStream(),true);
				out.println("stat");
				BufferedReader in = new BufferedReader(new InputStreamReader(s1.getInputStream()));
				String reply = in.readLine();
				s1.close();
				System.out.println(reply);
				if(reply.equals("0")){
					isOpen=false;
					theGUI.setVisible(false);
					//startNormal();
					//timer.cancel();
					main(null);
				}
			Socket s1 = listen.accept();
			Thread t = new ServersC(s1);
			
			t.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	public static void startsP() throws IOException{
		File file = new File("broadcast.jar");
		String path11 = (file.getAbsolutePath());
		String path21 = path11.substring(0,path11.lastIndexOf('\\'));
		String path31 = path21+"\\src\\broadcast.jar";
		ProcessBuilder pb= new ProcessBuilder("java","-jar",path31);
		pb.start();
		
	}

	public static void setLbl(BufferedImage bi) {
		// pic.setIcon(null);
		pic.setIcon(new ImageIcon(bi));

	}

}

// Class Image server for receive Lecture Broadcast

class ServersC extends Thread {
	private Socket socket = new Socket();
	private String client;
	int i = 0;

	ServersC(Socket s) {
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
/*				BufferedImage img1 = new BufferedImage(1280, 720,);
				Graphics2D g2d = out*/
				BufferedImage resize = Scalr.resize(img,Mode.AUTOMATIC,1320,714);
				UASJavaClient.setLbl(resize);
				UASJavaClient.sambilJalan();

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
	