import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

/**
 * This class controls the GUI and button actions in the program.
 * It creates the JFrame, JPanel, and buttons.
 */
public class ClimbingClub extends JFrame implements ActionListener {

	/**
	 * four array lists:
	 * 1) list of all Climbs
	 * 2) list of all hikers
	 * 3) list of all mountains climbed
	 * 4) list of all the items bought in the shop
	 */
	private ArrayList<ClimbInfo> list = new ArrayList<ClimbInfo>();
	private ArrayList<String> hikerList = new ArrayList<String>();
	private ArrayList<String> mntnList = new ArrayList<String>();
	private ArrayList<Shop> shopList = new ArrayList<Shop>();
	
	JFrame f, t, s;
	//f-full display
	//t-the JTable
	//s-shop
	JTextArea ja;
	JLabel intro, coin, spH, time, space;
	JButton disp, add, shop, sh, mntn, rem, pic,
			s1, s2, s3, s4, s5, s6;
	ImageIcon pict, pic1, pic2, pic3, pic4, pic5, 
		scaledIcon, scaledIcon1, scaledIcon2, scaledIcon3, scaledIcon4, scaledIcon5;
	private int coins = 0;
	private double totalTime = 0;  
	
	public ClimbingClub() {
		welcome();
		display();
	}
		
	public void welcome() {
		JOP.msg("Welcome to the CLIMBING CLUB!"
				+ "\n\nWhat can you do at the Climbing Club?"
				+ "\n • Add a hiker and their climb data!"
				+ "\n • See the hiking data of all climbers!"
				+ "\n • Gain coins to buy hiking supplies for the club from the shop!"
				+ "\n   (gain 1 coin per minute of climbing)"
				+ "\n\nULTIMATELY..."
				+ "\n • The total hiking time of the group will decide whether"
				+ "\n   the club will be able to reach the summit of our mountain!"
				+ "\n   (click mountain for details)."
				+ "\n\nHAVE FUN CLIMBING, HIKERS!!"
				+ "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
				+ "\n\nPS: Click the 'x' on the top right corner to exit the club"
				+ "\nat any time.");
	}
	
	private Image scaleImage(Image image, int w, int h) {
	    Image scaled = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
	    return scaled;
	}
	
	//creates display (frame, panel, buttons, and pictures)
	public void display() {
		f = new JFrame();
		f.setLayout(new FlowLayout());
		f.setTitle("The Climbing Club");
		f.setSize(600, 600);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pict = new ImageIcon(getClass().getResource("b.jpg"));
		int width = pict.getIconWidth() / 4;
		int height = pict.getIconHeight() / 4;
		Image scaled = scaleImage(pict.getImage(), width, height);
		scaledIcon = new ImageIcon(scaled);
		
		pic1 = new ImageIcon(getClass().getResource("b1.jpg"));
		int width1 = pic1.getIconWidth() / 4;
		int height1 = pic1.getIconHeight() / 4;
		Image scaled1 = scaleImage(pic1.getImage(), width1, height1);
		scaledIcon1 = new ImageIcon(scaled1); 
		
		pic2 = new ImageIcon(getClass().getResource("b2.jpg"));
		int width2 = pic2.getIconWidth() / 4;
		int height2 = pic2.getIconHeight() / 4;
		Image scaled2 = scaleImage(pic2.getImage(), width2, height2);
		scaledIcon2 = new ImageIcon(scaled2);
		
		pic3 = new ImageIcon(getClass().getResource("b3.jpg"));
		int width3 = pic3.getIconWidth() / 4;
		int height3 = pic3.getIconHeight() / 4;
		Image scaled3 = scaleImage(pic3.getImage(), width3, height3);
		scaledIcon3 = new ImageIcon(scaled3);
		
		pic4 = new ImageIcon(getClass().getResource("b4.jpg"));
		int width4 = pic4.getIconWidth() / 4;
		int height4 = pic4.getIconHeight() / 4;
		Image scaled4 = scaleImage(pic4.getImage(), width4, height4);
		scaledIcon4 = new ImageIcon(scaled4);
		
		pic5 = new ImageIcon(getClass().getResource("b5.jpg"));
		int width5 = pic5.getIconWidth() / 4;
		int height5 = pic5.getIconHeight() / 4;
		Image scaled5 = scaleImage(pic5.getImage(), width5, height5);
		scaledIcon5 = new ImageIcon(scaled5);
		
		
		intro = new JLabel("                                    Welcome to the Climbing Club!                                  ");
		disp = new JButton("Full List of Hikers & Climbs");
		sh = new JButton("View by Specific Hiker");
		mntn = new JButton("View by Mountain");
		add = new JButton("Add a Climb");
		rem = new JButton("Remove a Climb or Hiker");
		shop = new JButton("Shop");
		coin = new JLabel("Coins: " + coins);
		pic = new JButton(scaledIcon);
		time = new JLabel("Total Time: " + totalTime + " minutes");
		space = new JLabel("           ");
		
		f.add(intro);
		f.add(disp);
		f.add(sh);
		f.add(mntn);
		f.add(pic);
		f.add(add);
		f.add(rem);
		f.add(shop);
		f.add(coin);
		f.add(space);
		f.add(time);
		
		disp.addActionListener(this);
		add.addActionListener(this);
		rem.addActionListener(this);
		sh.addActionListener(this);
		mntn.addActionListener(this);
		shop.addActionListener(this);
		pic.addActionListener(this);
		
		disp.setPreferredSize(new Dimension(250, 50));
		sh.setPreferredSize(new Dimension(250, 50));
		mntn.setPreferredSize(new Dimension(250, 50));
		add.setPreferredSize(new Dimension(250, 50));
		rem.setPreferredSize(new Dimension(250, 50));
		shop.setPreferredSize(new Dimension(250, 50));
		pic.setPreferredSize(new Dimension(350, 350));
		
		f.setVisible(true);
	}
	
	//controls the actions of the buttons
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == disp) {
			fullList();
		}
		if(e.getSource() == add) {
			String name = JOP.in("Enter the hiker's name.");
			String peak = JOP.in("Enter the name of the peak.");
			String t = JOP.in("Enter the time (in minutes) that the climb took.");
			double time = Double.parseDouble(t);
			addToList(name, peak, time);
			coin.setText("Coins: " + coins);
			this.time.setText("Total Time: " + totalTime);
			
			if(totalTime >= 10000) {
				pic.setIcon(scaledIcon5);
				JOP.msg("CONGRATS, YOU REACHED THE SUMMIT OF THE MOUNTAIN!"
						+ "\nYOU WILL EARN 500 EXTRA COINS!");
				coins += 5000;
				coin.setText("Coins: " + coins);
			}
			else if(totalTime < 10000 && totalTime >= 5000) {
				pic.setIcon(scaledIcon4);
			}
			else if(totalTime < 5000 && totalTime >= 2500) {
				pic.setIcon(scaledIcon3);
			}
			else if(totalTime < 2500 && totalTime >= 1000) {
				pic.setIcon(scaledIcon2);
			}
			else if(totalTime < 1000 && totalTime >= 500) {
				pic.setIcon(scaledIcon1);
			}
			else if(totalTime < 500 && totalTime >= 0){
				pic.setIcon(scaledIcon);
			}
			
		}
		if(e.getSource() == sh) {
			String in = JOP.in("List of Hikers\n" + specHiker() 
			+ "\nWhich hiker's data would you like to see?\nEnter the number.");
			int index = Integer.parseInt(in) - 1;
			JOP.msg(getHiker(index));
		}
		if(e.getSource() == mntn) {
			String in = JOP.in("List of Mountains\n" + specMntn() 
			+ "\nWhich mountain's data would you like to see?\nEnter the number.");
			int index = Integer.parseInt(in) - 1;
			JOP.msg(getMntn(index));
		}
		if(e.getSource() == rem) {
			if(list.size() == 0) {
				JOP.msg("Sorry, there are no items on the list to remove.");
			}
			else {
				String remove = JOP.in("Would you like to remove from the list by: "
						+ "\n1) Hiker \n2) Mountain\n3) A specific climb on the list"
						+ " \nEnter the number on the list.");
				if(remove.equals("1")) {
					String remH = JOP.in(specHiker() + "Which hiker would you like to remove?"
							+ "\nEnter the number");
					int remHI = Integer.parseInt(remH) - 1;
					removeHiker(remHI);
					coin.setText("Coins: " + coins);
					this.time.setText("Total Time: " + totalTime);
					hikerList.remove(remHI);
				}
				if(remove.equals("2")) {
					String remM = JOP.in(specMntn() + "Which mountain would you like to remove?"
							+ "\nEnter the number");
					int remMI = Integer.parseInt(remM) - 1;
					removeMntn(remMI);
					coin.setText("Coins: " + coins);
					this.time.setText("Total Time: " + totalTime);
					mntnList.remove(remMI);
				}
				if(remove.equals("3")) {
					String remS = JOP.in(dispFullList() + "\nWhich specific climb would you like to remove?"
							+ "\nEnter the number");
					int remSI = Integer.parseInt(remS) - 1;
					remove(remSI);
					coin.setText("Coins: " + coins);
					this.time.setText("Total Time: " + totalTime);
					
				} 
			}
			
			if(totalTime >= 10000) {
				pic.setIcon(scaledIcon5);
				JOP.msg("CONGRATS, YOU REACHED THE SUMMIT OF THE MOUNTAIN!"
						+ "\nYOU WILL EARN 100 EXTRA COINS!");
				coins += 100;
				coin.setText("Coins: " + coins);
			}
			else if(totalTime < 10000 && totalTime >= 5000) {
				pic.setIcon(scaledIcon4);
			}
			else if(totalTime < 5000 && totalTime >= 2500) {
				pic.setIcon(scaledIcon3);
			}
			else if(totalTime < 2500 && totalTime >= 1000) {
				pic.setIcon(scaledIcon2);
			}
			else if(totalTime < 1000 && totalTime >= 500) {
				pic.setIcon(scaledIcon1);
			}
			else if(totalTime < 500 && totalTime >= 0) {
				pic.setIcon(scaledIcon);
			}
		}
		if(e.getSource() == pic) {
			JOP.msg("HOW TO REACH THE PEAK OF OUR MOUNTAIN (as a club):"
				  + "\n\nLEVEL:                TOTAL CLIMBING MINUTES NEEDED:"
					+ "\nLevel 1                500 minutes"
					+ "\nLevel 2                1000 minutes"
					+ "\nLevel 3                2500 minutes"
					+ "\nLevel 4                5000 minutes"
					+ "\nLevel 5                10000 minutes"
					+ "\n\nThe colors of the levels on the mountain will "
					+ "\nchange once you start adding climbing time!");
		}
		if(e.getSource() == shop) {
			shop();
			
		}
		if(e.getSource() == s1) {
			if(coins == 0 || coins < 20) {
				JOP.msg("Sorry, you don't have enough coins to buy this item.");
			}
			else {
				shopList.add(new Shop("Backpack", 20)); 
				coins -= 20;
				ja.setText("Coins: " + coins + "\n" + dispShopList());
				coin.setText("Coins: " + coins);
			}
			
		}
		if(e.getSource() == s2) {
			if(coins == 0 || coins < 30) {
				JOP.msg("Sorry, you don't have enough coins to buy this item.");
			}
			else {
				shopList.add(new Shop("Hiking Shoes, 1 pair", 30));
				coins -= 30;
				ja.setText("Coins: " + coins + "\n" +dispShopList());
				coin.setText("Coins: " + coins);
			}
		}
		if(e.getSource() == s3) {
			if(coins == 0 || coins < 50) {
				JOP.msg("Sorry, you don't have enough coins to buyt this item.");
			}
			else {
				shopList.add(new Shop("Water Bottles, 6 pk", 50));
				coins -= 50;
				ja.setText("Coins: " + coins + "\n" + dispShopList());
				coin.setText("Coins: " + coins);
			}
		}
		if(e.getSource() == s4) {
			if(coins == 0 || coins < 60) {
				JOP.msg("Sorry, you don't have enough coins to buyt this item.");
			}
			else {
				shopList.add(new Shop("Navigation Tool Kit", 60));
				coins -= 60;
				ja.setText("Coins: " + coins + "\n" + dispShopList());
				coin.setText("Coins: " + coins);
			}
		}
		if(e.getSource() == s5) {
			if(coins == 0 || coins < 100) {
				JOP.msg("Sorry, you don't have enough coins to buyt this item.");
			}
			else {
				shopList.add(new Shop("Complete Hiking Kit", 100));
				coins -= 100;
				ja.setText("Coins: " + coins + "\n" + dispShopList());
				coin.setText("Coins: " + coins);
			}
				
		}
		if(e.getSource() == s6) {
			String remove = JOP.in(dispShopList() + "\nWhich item would you like to remove?"
					+ "\nEnter the number.");
			int remInd = Integer.parseInt(remove) - 1;
			coins += shopList.get(remInd).getPrice();
			shopList.remove(remInd);
			ja.setText("Coins: " + coins + "\n" + dispShopList() + "\n");
			coin.setText("Coins: " + coins);
		}
			
	}
	
	///////////////////////////////////////////////////////////////
	//ADDS TO THE LIST OF ALL CLIMBS
	public void addToList(String name, String peakName, double time) {
		for (int i = 0; i < list.size(); i++) {
			if (name.compareTo(list.get(i).getName()) <= 0) {
				list.add(i, new ClimbInfo(name, peakName, time));
				totalTime += time;
				coins = (int)totalTime;
				return;
			}
		}
		list.add(new ClimbInfo(name, peakName, time));
		totalTime += time;
		coins = (int)totalTime;
	}
	
	//displays full list of climbs
	public String dispFullList() {
		String s = "";
		for(int i = 0; i < list.size(); i++) {
			s += (i+1) + ") " + list.get(i).getName() + " --> " + list.get(i).getPeakName()
					+ " --> " + list.get(i).getTime() + " minutes\n";
		}
		
		return s;
	}
	
	//removes a climb from the list
	public void remove(int n) {
		totalTime -= list.get(n).getTime();
		for(int i = 0; i < mntnList.size(); i++) {
			if(mntnList.get(i).equals(list.get(n).getPeakName()))
				mntnList.remove(list.get(n).getPeakName());
		}
		for(int i = 0; i < hikerList.size(); i++) {
			if(hikerList.get(i).equals(list.get(n).getName()))
				hikerList.remove(list.get(n).getName());
		}
		coins = (int)totalTime;
		list.remove(n);
	}
	
	////////////////////////////Hikers/////////////////////////////////////
	//MAKES ARRAYLIST OF ALL HIKERS 
	public ArrayList<String> hikerList() {
		for(int i = 0; i < list.size(); i++) {
			if(!hikerList.contains(list.get(i).getName())) {
				hikerList.add(list.get(i).getName());
			}
		}
		return hikerList;
	}
	
	//GETS THE STRING LIST OF ALL HIKERS 
	public String specHiker() {
		String specHiker = "";
		for(int i = 0; i < hikerList().size(); i++) {
			specHiker += i+1 + ") " + hikerList().get(i) + "\n";
		}
		return specHiker;
	}
	
	
	//GETS DATA OF SPECIFIC HIKER
	public String getHiker(int s) {
		String string = "";
		String name = hikerList().get(s);
		String hiker = "Total time of " + name + ": ";
		String mntn = "All mountains climbed by " + name + ": \n";
		String mntn1 = "";
		double hikerT = 0;
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
				hikerT += list.get(i).getTime();
				if(mntn1.indexOf(list.get(i).getPeakName()) == -1) {
					mntn1 += list.get(i).getPeakName() + "\n";
				}
			}
		}
		string += hiker + String.valueOf(hikerT) + "\n" + mntn + mntn1;
		return string;
	}
	
	//REMOVE A SPECIFIC HIKER
	public void removeHiker(int n) {
		String name = hikerList.get(n);
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getName().equals(name)) {
				totalTime -= list.get(i).getTime();
				coins = (int)totalTime;
				list.remove(i);
				i--;
			}
		}
	}
	
	/////////////////////////////Mountains////////////////////////////////////
	//MAKE ARRAYLIST OF ALL MNTNS
	public ArrayList<String> mntnList() {
		for(int i = 0; i < list.size(); i++) {
			if(!mntnList.contains(list.get(i).getPeakName())) {
				mntnList.add(list.get(i).getPeakName());
			}
		}
		return mntnList;
	}
	
	//GETS LIST OF ALL MOUNTAINS 
	public String specMntn() {
		String specMntn = "";
		for(int i = 0; i < mntnList().size(); i++) {
			specMntn += i+1 + ") " + mntnList().get(i) + "\n";
		}
		return specMntn;
	}
	
	//GETS DATA OF SPECIFIC Mountain 
		public String getMntn(int s) {
			String string = "";
			String mntn = mntnList().get(s);
			String hiker = "Total time on " + mntn + ": ";
			String name = "All the people who climbed " + mntn + ": \n";
			String name1 = "";
			double mntnT = 0;
			
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getPeakName().equals(mntn)) {
					mntnT += list.get(i).getTime();
					if(name1.indexOf(list.get(i).getName()) == -1) {
						name1 += list.get(i).getName() + "\n";
					}
				}
			}
			string += hiker + String.valueOf(mntnT) + "\n" + name + name1;
			return string;
		}
		
		//REMOVE A SPECIFIC MOUNTAIN
		public void removeMntn(int n) {
			String name = mntnList.get(n);
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getPeakName().equals(name)) {
					totalTime -= list.get(i).getTime();
					coins = (int)totalTime;
					list.remove(i);
					i--;
				}
			}
		}
	
	//GETS JTable/JFrame LIST OF ALL HIKERS, MNTNS, AND TIMES
	public JFrame fullList() {
		t = new JFrame();
		String row[][] = new String[list.size()][3];
		int c = 0;
		for(int i = 0; i < list.size(); i++) {
			row[i][c] = list.get(i).getName();
			row[i][c+1] = list.get(i).getPeakName();
			row[i][c+2] = String.valueOf(list.get(i).getTime()) + " minutes";
		} 
		String column[]={"HIKER","PEAK","TIME"};         
		JTable jt = new JTable(row, column);              
		JScrollPane sp = new JScrollPane(jt);    
		t.add(sp);          
		t.setSize(300,400);    
		t.setVisible(true);
		t.setLocationRelativeTo(null);
		return t;
	}
	
	//MAKING JFRAME OF THE SHOP
	public JFrame shop() {
		s = new JFrame();
		s.setLayout(new FlowLayout());
		s.setTitle("Climbing Club's Shop");
		s.setSize(500, 500);
		s.setLocationRelativeTo(null);
		s.setVisible(true);
		
		s1 = new JButton("Backpack (20 coins)");
		s2 = new JButton("Hiking Shoes - 1 pair (30 coins)");
		s3 = new JButton("Water Bottles 6 pk (50 coins)");
		s4 = new JButton("Navigation Tool Kit (60 coins)");
		s5 = new JButton("Complete Hiking Kit (100 coins)");
		s6 = new JButton("Remove Item");
		ja = new JTextArea("Coins: " + coins + "\n" + dispShopList() + "\n");
		ja.setLineWrap(true);
		ja.setBounds(200, 200, 350, 350);
		
		s.add(s1);
		s.add(s2);
		s.add(s3);
		s.add(s4);
		s.add(s5);
		s.add(s6);
		s.add(ja);
	
		s1.addActionListener(this);
		s2.addActionListener(this);
		s3.addActionListener(this);
		s4.addActionListener(this);
		s5.addActionListener(this);
		s6.addActionListener(this);
		
		s1.setPreferredSize(new Dimension(220, 50));
		s2.setPreferredSize(new Dimension(220, 50));
		s3.setPreferredSize(new Dimension(220, 50));
		s4.setPreferredSize(new Dimension(220, 50));
		s5.setPreferredSize(new Dimension(220, 50));
		s6.setPreferredSize(new Dimension(220, 50));
		
		return s;
	}
	
	//displays the shopping arraylist
	public String dispShopList() {
		String sl = "";
		for(int i = 0; i < shopList.size(); i++) {
			sl += (i+1) + ") " + shopList.get(i).getItem() + " -- " 
		+ shopList.get(i).getPrice() + " coins\n";
		}
		return sl;
	}
	
	
}

