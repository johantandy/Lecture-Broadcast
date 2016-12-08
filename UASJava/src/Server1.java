import java.awt.AWTException;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.SliderUI;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Mode;

public class Server1 extends JFrame {
	static int sPort = 9090;
	static int[] PORT = new int[1000];
	static JLabel pic = new JLabel();
	static JLabel[] pic1 = new JLabel[100];
	static int n = 0;
	static int x = 50;
	static Socket[] s = new Socket[1000];
	static int activeClient = 0;
	static ServerSocket[] listener = new ServerSocket[1000];
	static String[] ipClient = new String[1000];
	static String stat = "0";
	static boolean ioWrite=false;
	static BufferedImage bis1;
	static BufferedImage bis2;
	static String namaDosen;
	static String mataKuliah;
	static String title;
	static JFrame theGUI;
	private JPanel contentPane;
	
	public static void setDosen(){
		namaDosen=Login.namaDosen;
		mataKuliah=Login.mataKuliah;
/*		namaDosen="Johan Tandy";
		mataKuliah="Machine Learning";*/
		title="Dosen : "+namaDosen+",  Mata Kuliah : "+mataKuliah;
		
	}
	
	public Server1() throws IOException, AWTException{
		setDosen();
		//JOptionPane.showMessageDialog(null,namaDosen+" "+mataKuliah);
		Robot robot = new Robot();
		
		setTitle("Lecture Screen Broadcast");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0,0,1280,720);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane=new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//theGUI = new JFrame();
		//theGUI.setLayout(null);

		pic.setBounds(50, 150, 640, 480);
		pic1[1] = new JLabel();
		pic1[2] = new JLabel();
		pic1[1].setBounds(25, 40, 800, 600);
		pic1[2].setBounds(700, 40, 800, 600);
		//theGUI.setTitle("Lecture Broadcast");
		//theGUI.setSize(1280, 720);
		//theGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//theGUI.setUndecorated(true);
		//theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//theGUI.setVisible(true);
		//theGUI.add(pic);
		//theGUI.add(pic1[1]);
		//theGUI.add(pic1[2]);
		
pic1[2].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ShowFullscreen.main(null,2);
			}
		});
		
		pic1[1].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ShowFullscreen.main(null,1);
			}
		});
		
		JButton button1 = new JButton("End Broadcast");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stat = "0";
				ioWrite=false;
				/*try {
					startServer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
		});

		JButton button = new JButton("Broadcast");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stat = "1";
					ioWrite=true;
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// TODO Auto-generated method stub
				/*
				 * JLabel _lbl = new JLabel("test"); x = x + n;
				 * _lbl.setBounds(x, 150, 320, 240); theGUI.add(_lbl);
				 * theGUI.validate(); theGUI.repaint(); // theGUI.pack(); n =
				 * 320; // x=x+n; System.out.println(x);
				 */
				
				Timer timer = new Timer();

				Rectangle screenact = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							for (int i = 1; i <= activeClient; i++) {
								
								System.out.println(activeClient);
								Socket ss = new Socket(ipClient[i],8998);
								if(ioWrite==true){
								BufferedImage capture1 = robot.createScreenCapture(screenact);
								
								ImageIO.write(capture1, "png",ss.getOutputStream() /*new File("Image.png")*/);
								}
								/*byte[] mybytearray = new byte[1024];
							    InputStream is = ss.getInputStream();
							    FileOutputStream fos = new FileOutputStream("s.pdf");
							    BufferedOutputStream bos = new BufferedOutputStream(fos);
							    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
							    bos.write(mybytearray, 0, bytesRead);
							    bos.close();
							    ss.close();*/
							}

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							//JOptionPane.showMessageDialog(null, String.valueOf(e1.toString()));
							//timer.cancel();
						//e1.printStackTrace();
						}
					}
				}, 1, 1);

			}
		});
		button.setBounds(20, 20, 100, 40);
		button1.setBounds(140, 20, 150, 40);
		contentPane.add(button);
		contentPane.add(button1);
		contentPane.add(pic1[1]);
		contentPane.add(pic1[2]);
				
		
	

		System.out.println("The server is running on port" + PORT);
		
		//portServer();
		//statServer();
		
	}
	
	

	public static void main(String[] args) throws IOException, AWTException {
		/*// TODO Auto-generated method stub
		setDosen();
		//JOptionPane.showMessageDialog(null,namaDosen+" "+mataKuliah);
		Robot robot = new Robot();
		JFrame theGUI = new JFrame();
		theGUI.setLayout(null);

		pic.setBounds(50, 150, 640, 480);
		pic1[1] = new JLabel();
		pic1[2] = new JLabel();
		pic1[1].setBounds(25, 80, 640, 480);
		pic1[2].setBounds(700, 80, 640, 480);
		theGUI.setTitle("Lecture Broadcast");
		theGUI.setSize(1280, 720);
		theGUI.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//theGUI.setUndecorated(true);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		theGUI.setVisible(true);
		theGUI.add(pic);
		theGUI.add(pic1[1]);
		theGUI.add(pic1[2]);
		
pic1[2].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ShowFullscreen.main(null,2);
			}
		});
		
		pic1[1].addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				ShowFullscreen.main(null,1);
			}
		});
		
		JButton button1 = new JButton("End Broadcast");
		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stat = "0";
				ioWrite=false;
				try {
					startServer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JButton button = new JButton("Broadcast");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					stat = "1";
					ioWrite=true;
					TimeUnit.SECONDS.sleep(3);
				} catch (InterruptedException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				// TODO Auto-generated method stub
				
				 * JLabel _lbl = new JLabel("test"); x = x + n;
				 * _lbl.setBounds(x, 150, 320, 240); theGUI.add(_lbl);
				 * theGUI.validate(); theGUI.repaint(); // theGUI.pack(); n =
				 * 320; // x=x+n; System.out.println(x);
				 
				
				Timer timer = new Timer();

				Rectangle screenact = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							for (int i = 1; i <= activeClient; i++) {
								
								System.out.println(activeClient);
								Socket ss = new Socket(ipClient[i],8998);
								if(ioWrite==true){
								BufferedImage capture1 = robot.createScreenCapture(screenact);
								
								ImageIO.write(capture1, "png",ss.getOutputStream() new File("Image.png"));
								}
								byte[] mybytearray = new byte[1024];
							    InputStream is = ss.getInputStream();
							    FileOutputStream fos = new FileOutputStream("s.pdf");
							    BufferedOutputStream bos = new BufferedOutputStream(fos);
							    int bytesRead = is.read(mybytearray, 0, mybytearray.length);
							    bos.write(mybytearray, 0, bytesRead);
							    bos.close();
							    ss.close();
							}

						} catch (IOException e1) {
							// TODO Auto-generated catch block
							//JOptionPane.showMessageDialog(null, String.valueOf(e1.toString()));
							//timer.cancel();
						//e1.printStackTrace();
						}
					}
				}, 1, 1);

			}
		});
		button.setBounds(20, 20, 100, 40);
		button1.setBounds(140, 20, 150, 40);
		theGUI.add(button);
		theGUI.add(button1);
	

		System.out.println("The server is running on port" + PORT);*/
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server1 s = new Server1();
					s.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
		Server1 s = new Server1();
		s.setVisible(true);
		try {
			TimeUnit.SECONDS.sleep(1);
			//portServer();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					portServer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		t.start();
		//portServer();
		//statServer();
		
		

	}
	
	// Set Image To JFrame Label.

	public static void setLbl(BufferedImage bi) {
		pic.setIcon(new ImageIcon(bi));

	}

	public static void setlbl1(BufferedImage bi, int i) {
		//System.out.println("receive "+i);
		pic1[i].setIcon(new ImageIcon(bi));
	}
	
	//Save Client IP

	public static void setIP(String ip, int i) {
		ipClient[i] = ip;
	}
	
	public static void bis(BufferedImage bi,int i){
		if(i==1){
		 bis1=bi;
		}
		if(i==2){
		bis2=bi;
		}
		//return bis1;
	}
	
	public static BufferedImage retImage(){
		return bis1;
	}
	
	public static BufferedImage retImage1(){
		return bis2;
	}
	
	//Increment PORT number 

	public static int servPort() throws IOException {
		sPort = sPort + 2;
		PORT[activeClient + 1] = sPort;
		listener[activeClient + 1] = new ServerSocket(PORT[activeClient + 1]);
		System.out.println("Given port " + activeClient + " " + PORT[activeClient + 1]);
		System.out.println(sPort);
		System.out.println(listener[activeClient + 1]);
		return sPort;
	}

	//Return Status Lecturing Broadcast
	
	public static String getStat() {
		return stat;
	}
	
	//Return count ActiveClient

	public static int retActiveClient() {
		return activeClient;
	}
	
	public static void iterClient(){
		activeClient++;
	}

	// Method for starting Image Server

	public static void startServer() throws IOException {
		//System.out.println("Starting server on port " + PORT[activeClient + 1]);
		//System.out.println(activeClient);
		try {
			while (true) {
				for (int i = 1; i <= activeClient; i++) {
					s[i] = listener[i + 1].accept();
					Thread t = new Servers(s[i], i);
					t.start();
				}

			}
		} finally {
			// TODO: handle exception
			// listener.close();
		}
	}

	// Method for Starting Port Service

	public static void portServer() throws IOException {
		ServerSocket ListenerSoPort = new ServerSocket(8999);
		System.out.println(ListenerSoPort);
		try {
			while (true) {
				Socket soPort = ListenerSoPort.accept();
				Thread tSPort = new portServer(soPort);
				tSPort.start();
				//System.out.println("Active client"+activeClient);

			}
		} finally {
			ListenerSoPort.close();
		}

	}
	
	// Method for starting status server
	
	public static void statServer() throws IOException {
		ServerSocket ListenerSoPorts = new ServerSocket(8997);
		System.out.println(ListenerSoPorts);
		try {
			while (true) {
				Socket soPorts = ListenerSoPorts.accept();
				Thread tSPorts = new statServer(soPorts);
				tSPorts.start();

			}
		} finally {
			ListenerSoPorts.close();
		}

	}

}

// Class Main Image Server

class Servers extends Thread {
	private Socket[] socket = new Socket[1000];
	private String client;
	BufferedImage[] img = new BufferedImage[100];
	BufferedImage[] resize=new BufferedImage[100];
	int i = 0;

	Servers(Socket s, int ii) {
		socket[ii] = s;
		i = ii;
		client = s.getRemoteSocketAddress().toString();
		//System.out.println("Incoming Image from " + client);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				// for (int i = 1; i <= Server1.retActiveClient(); i++) {
				// System.out.println(Server1.retActiveClient());
				//if(ImageIO.read(ImageIO.createImageInputStream(socket[i].getInputStream()))!=null){
				try {
					img[i] = ImageIO.read(ImageIO.createImageInputStream(socket[i].getInputStream()));
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				// Server1.setLbl(img[i]);
				//}
				//if(img[i]!=null){
				resize[i] = Scalr.resize(img[i],Mode.AUTOMATIC,640,480);
					Server1.setlbl1(resize[i], i);
					Server1.bis(img[i],i);
					//img[i]=null;
				//}
				
				// }

			}
		} catch (Exception e) {
			// TODO: handle exception
			// e.printStackTrace();
		}

		finally {
			try {
				socket[i].close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//System.out.println("close Connection from " + client);
		}

	}

}

// Class Port Serving Server

class portServer extends Thread {
	private Socket socket;
	private String client;
	BufferedImage[] img = new BufferedImage[100];

	portServer(Socket s) {
		socket = s;
		client = s.getRemoteSocketAddress().toString();
		String ipC = s.getInetAddress().toString();
		String ipC1 = ipC.replace("/", "");

		Server1.setIP(ipC1, Server1.retActiveClient() + 1);
		//System.out.println("req port: " + s.getRemoteSocketAddress().toString());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			while(true){
				
				String op = in.readLine();
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				if(op.equals("port")){
					Server1.iterClient();
					out.println(Server1.servPort());
				}
				if(op.equals("title")){
					out.println(Server1.title);
				}
				if(op.equals("stat")){
					out.println(Server1.getStat());
				}
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
		}

		finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Server1.startServer();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("close Connection from " + client);
		}

	}
}

// Class status Reply for Lecture Broadcast Screen 

class statServer extends Thread {
	private Socket socket;
	private String client;

	statServer(Socket s) {
		socket = s;
		client = s.getRemoteSocketAddress().toString();
		System.out.println("Request stats " + s.getRemoteSocketAddress().toString());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(Server1.getStat());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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
