/**
 * 
 * @author Choong-Soo Lee
 *
 * Provides a stop watch to start/stop and take a difference in ns, ms and s
 */
public class StopWatch {
	// instance variables
	private long startTime, stopTime;
	
	// constructors
	/**
	 * Default constructor
	 * 
	 * Sets the start and stop time to 0
	 */
	public StopWatch() {
		startTime = 0;
		stopTime = 0;
	}
	
	/**
	 * Gets the current nanotime
	 */
	private long currentTime() {
		return System.nanoTime();
	}
	
	/**
	 * Start the stop watch
	 */
	public void start() {
		startTime = currentTime();
	}
	
	/**
	 * Stop the stop watch
	 */
	public void stop() {
		stopTime = currentTime();
	}
	
	/**
	 * Computes elapsed time in nanoseconds
	 * 
	 * @return time between start and stop in nanoseconds
	 */
	public long getElapsedTimeNanos() {
		return stopTime - startTime;
	}
	
	/**
	 * Computes elapsed time in milliseconds
	 * 
	 * @return time between start and stop in milliseconds
	 */	
	public double getElapsedTimeMillis() {
		return getElapsedTimeNanos() / 1000000.0;
	}

	/**
	 * Computes elapsed time in seconds
	 * 
	 * @return time between start and stop in seconds
	 */
	public double getElapsedTime() {
		return getElapsedTimeNanos() / 1000000000.0;
	}
	
}
