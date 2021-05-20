
/**
 * This class creates the information for a climb.
 * It saves the name of the hiker, the name of the peak,
 * and the duration of the climb.
 */
public class ClimbInfo {
	
	private String name;
	private String peakName;
	private double time;
	
	public ClimbInfo(String n, String pn, double t) {
		name = n;
		peakName = pn;
		time = t;
	}
	
	//getter methods for name, peak name, and duration
	public String getName() { return name; }
	public String getPeakName() { return peakName; }
	public double getTime() { return time; }
	
}
	
