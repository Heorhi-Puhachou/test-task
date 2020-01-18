package duallab.busstop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import duallab.busstop.constant.Company;
import duallab.busstop.entity.Record;
import duallab.busstop.util.Util;

public class App {

	private static Logger log = Logger.getLogger(App.class.getName());
	private static String OUTPUT_FILENAME = "output.txt";

	public static void main(String[] args) {

		if (args.length == 0) {
			logException("Please set path to input file.");
			return;
		}

		TreeSet<Record> records = new TreeSet<>();
		Path filePath = Paths.get(args[0]);
		try (BufferedReader br = new BufferedReader(new FileReader(filePath.toString()))) {
			for (String line; (line = br.readLine()) != null;) {
				Record tempRecord = new Record(line);
				if (!longerThanHour(tempRecord)) {
					Util.searchMoreEfficientAndAdd(records, tempRecord);
				}
			}
		} catch (FileNotFoundException exception) {
			logException(exception);
		} catch (IOException exception) {
			logException(exception);
		}

		// Output file will be created near with input file.
		String outputFilePath = filePath.getParent().toString() + OUTPUT_FILENAME;
		ArrayList<Record> grottyRecords = new ArrayList<>();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
			Iterator<Record> itr = records.iterator();
			while (itr.hasNext()) {
				Record tempRecord = itr.next();
				// Posh Bus Company section first followed...
				if (tempRecord.getCompanyName().equals(Company.POSH.getCompanyName())) {
					writer.write(tempRecord.toString());
					writer.newLine();
				} else {
					grottyRecords.add(tempRecord);
				}

			}
			// ...by a blank line and the Grotty Bus Company section
			writer.newLine();

			itr = grottyRecords.iterator();
			while (itr.hasNext()) {
				writer.write(itr.next().toString());
				writer.newLine();
			}
		} catch (IOException exception) {
			logException(exception);
		}
	}

	private static boolean longerThanHour(Record record) {
		return record.getArrivalTime().getAllMinutes() - record.getDepartureTime().getAllMinutes() > 60;
	}

	private static void logException(Exception exception) {
		logException(exception.getMessage());
	}

	private static void logException(String message) {
		log.log(Level.INFO, message);
	}
}
