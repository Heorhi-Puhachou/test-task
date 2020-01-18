package duallab.busstop.util;

import java.util.Iterator;
import java.util.TreeSet;

import duallab.busstop.constant.Company;
import duallab.busstop.entity.Record;

public class Util {

	/*
	 * Method get records and potential new record. After analyzing new record can
	 * be added and one old record can be removed.
	 */
	public static void searchMoreEfficientAndAdd(TreeSet<Record> records, Record newRecord) {
		if (records.size() == 0) {
			records.add(newRecord);
			return;
		}

		Iterator<Record> itr = records.iterator();
		while (itr.hasNext()) {
			Record oldRecord = itr.next();
			// New is better - add new and emove old
			if (isMoreEfficient(oldRecord, newRecord) == true) {
				itr.remove();
				break;
			}
			// Old is better - stop comparing
			else if (isMoreEfficient(newRecord, oldRecord) == true) {
				return;
			}
		}
		records.add(newRecord);
	}

	/*
	 * Only efficient services shall be added to the timetable. A service is
	 * considered efficient if there are no other services that are more efficient
	 * compared to it. A service is considered more efficient compared to the other
	 * one:
	 * 1)If it starts at the same time and reaches earlier
	 * or
	 * 2)If it starts later and reaches at the same time
	 * or
	 * 3)If it starts later and reaches earlier.
	 * 
	 * If both companies offer a service having the same departure and arrival times
	 * then always choose Posh Bus Company over Grotty Bus Company, since Grotty
	 * Bus Company busses are not as comfortable as those of Posh Bus Company
	 */
	public static boolean isMoreEfficient(Record oldRecord, Record newRecord) {
		// 1
		if (oldRecord.getDepartureTime().compareTo(newRecord.getDepartureTime()) == 0 &&
				(oldRecord.getArrivalTime().compareTo(newRecord.getArrivalTime()) == 1)) {
			return true;
		}
		// 2
		if (oldRecord.getDepartureTime().compareTo(newRecord.getDepartureTime()) == -1 &&
				(oldRecord.getArrivalTime().compareTo(newRecord.getArrivalTime()) == 0)) {
			return true;
		}
		// 3
		if (oldRecord.getDepartureTime().compareTo(newRecord.getDepartureTime()) == -1 &&
				(oldRecord.getArrivalTime().compareTo(newRecord.getArrivalTime()) == 1)) {
			return true;
		}
		// Grotty Bus Company busses are not as comfortable as those of Posh Bus Compan
		if (oldRecord.getDepartureTime().compareTo(newRecord.getDepartureTime()) == 0 &&
				(oldRecord.getArrivalTime().compareTo(newRecord.getArrivalTime()) == 0) &&
				oldRecord.getCompanyName().equals(Company.GROTTY.getCompanyName())) {
			return true;
		}

		return false;
	}

}
