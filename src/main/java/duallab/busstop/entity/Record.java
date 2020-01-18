package duallab.busstop.entity;

public class Record implements Comparable<Record> {
	private static String SPACE = " ";
	private static int COMPANY_NAME_INDEX = 0;
	private static int DEPARTURE_TIME_INDEX = 1;
	private static int ARRIVAL_TIME_INDEX = 2;

	private String originalString;
	private String companyName;
	private Time departureTime;
	private Time arrivalTime;

	public Record(String data) {
		originalString = data;
		String[] dataParts = data.split(SPACE);
		companyName = dataParts[COMPANY_NAME_INDEX];
		departureTime = new Time(dataParts[DEPARTURE_TIME_INDEX]);
		arrivalTime = new Time(dataParts[ARRIVAL_TIME_INDEX]);
	}

	public String getCompanyName() {
		return companyName;
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public Time getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public String toString() {
		return originalString;
	}

	@Override
	public int compareTo(Record anotherRecord) {
		return this.departureTime.compareTo(anotherRecord.getDepartureTime());
	}

}
