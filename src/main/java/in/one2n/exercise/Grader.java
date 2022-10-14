package in.one2n.exercise;

import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Grader {

	public List<Student> parseCSV(String filepath) {
		// TODO: add your implementation here
		
		// List of Student objects
		List<Student> students = new ArrayList<Student>();
		
		// Store the CSV data in String List
		List<String[]> allData;
		
		// Exception handler for the CSV path 
		// collect all the data to allData list and
		// convert this data to student object
		// further these student objects are stored in students list
		try {
			FileReader filereader = new FileReader(filepath);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();

			for (String[] row : allData) {
				Student student = new Student(row[0], row[1], row[2],
						Double.parseDouble(row[3]),Double.parseDouble(row[4]),
						Double.parseDouble(row[5]),Double.parseDouble(row[6]));
				students.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return students;
	}

	public List<Student> calculateGrade(List<Student> students) {
		// TODO: add your implementation here
		
		// generating a new list for students with final score
		List<Student> gradedStudents = List.copyOf(students);
		
		for (Student st : gradedStudents) {
			Double finalScore = st.getFinalScore();
			st.setFinalScore(finalScore);
		}
		return gradedStudents;
	}

	public Student findOverallTopper(List<Student> gradedStudents) {
		// TODO: add your implementation here
		
		Student student = null;
		Double score = Double.MIN_VALUE;
		for (Student st: gradedStudents) {
			if (st.getFinalScore() > score) {
				score = st.getFinalScore();
				student = st;
			}
		}
		return student;
	}

//	public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
//		// TODO: add your implementation here
//
//		Map<String, Student> univ = new HashMap<>();
//
//		for (int i = 0; i < gradedStudents.size(); i++) {
//			String key = gradedStudents.get(i).getUniversity();
//			if (univ.containsKey(key)) {
//				Double score = univ.get(key).getFinalScore();
//				for (int j = i; j < gradedStudents.size(); j++) {
//					if (key == gradedStudents.get(j).getUniversity()) {
//						Student st = gradedStudents.get(j);
//						if (score < st.getFinalScore()) {
//							univ.put(key, st);
//							score = st.getFinalScore();
//						}
//					}
//				}
//			} else {
//				univ.put(key, gradedStudents.get(i));
//			}
//		}
//
//		return univ;
//	}
	
	public Map<String, Student> findTopperPerUniversity(List<Student> gradedStudents) {
		// TODO: add your implementation here
		// using streams
		Map<String, Student> result = gradedStudents.stream()
	        .collect(Collectors.groupingBy(Student::getUniversity, Collectors.collectingAndThen(Collectors
	        		.maxBy(Comparator.comparing(Student::getFinalScore)), r -> r.get())));
		return result;
	}
}
