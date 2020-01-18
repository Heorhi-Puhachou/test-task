package duallab.busstop.entity;

public class Time implements Comparable<Time> {

	private static String DOTS = ":";
	private static int HOURS_INDEX = 0;
	private static int MINUTES_INDEX = 1;

	private String originalString;
	private int allMinutes;

	public Time(String timeString) {
		originalString = timeString;
		String[] timeStringParts = timeString.split(DOTS);
		allMinutes = Integer.parseInt(timeStringParts[HOURS_INDEX]) * 60 +
				Integer.parseInt(timeStringParts[MINUTES_INDEX]);
	}

	public int getAllMinutes() {
		return allMinutes;
	}

	@Override
	public String toString() {
		return originalString;
	}

	@Override
	public int compareTo(Time anotherTime) {
		if (this.allMinutes == anotherTime.allMinutes) {
			return 0;
		} else if (this.allMinutes < anotherTime.allMinutes) {
			return -1;
		} else {
			return 1;
		}
	}
}
