package hospital.patients;



import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import hospital.patients.AbstractPatient;

public class TestPatientQueue {
	

	//**************************************************************
	//********************** Helper method *************************
	//**************************************************************	
	/**
	 * Takes an array of patients and returns a shuffled copy.
	 * @param patients the patients
	 * 
	 * @return the patients in shuffled order
	 */
	private static AbstractPatient[] getShuffledArray(AbstractPatient[] patients){
		// Init random number generator with fixed seed
		Random random = new Random(12345);
		
		// Add patients to new list
		ArrayList<AbstractPatient> list = new ArrayList<AbstractPatient>();
		for (AbstractPatient p : patients){
			list.add(p);
		}		
		
		AbstractPatient[] shuffledArray = new AbstractPatient[list.size()];
		Collections.shuffle(list, random);
		list.toArray(shuffledArray);
		return shuffledArray;
	}
	

	//**************************************************************
	//****************** Test patient categories *******************
	//**************************************************************	
	
	// Check correct behavior of the getPriority() method 
	// for each patient class.
	@Test
	public void validateGetPriority(){
		System.out.println("Validating AbstractPatient.getPriority:\n");
		
		AbstractPatient p = new SlightlyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7));
		System.out.println("SlightlyInjuredPatient:\nExpected priority: " + TreatmentPriority.LOW.name());
		System.out.println("Found: " + p.getPriority().name());
		System.out.println((p.getPriority() == TreatmentPriority.LOW) ? "Valid" : "Invalid");

		p = new SeverelyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7), 0);
		System.out.println("SeverelyInjuredPatient:\nExpected priority: " + TreatmentPriority.MEDIUM.name());
		System.out.println("Found: " + p.getPriority().name());
		System.out.println((p.getPriority() == TreatmentPriority.MEDIUM) ? "Valid" : "Invalid");
		
		p = new EmergencyPatient("Edgar Friendly", LocalTime.of(7, 7));
		System.out.println("EmergencyPatient:\nExpected priority: " + TreatmentPriority.HIGH.name());
		System.out.println("Found: " + p.getPriority().name());
		System.out.println((p.getPriority() == TreatmentPriority.HIGH) ? "Valid\n" : "Invalid\n");
	}
	
	// Check correct behavior of the getCatInfo() method
	// for each patient class.
	@Test
	public void validateGetCatInfo(){
		System.out.println("Validating AbstractPatient.getCatInfo:\n");
		
		AbstractPatient p = new SlightlyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7));
		System.out.println("SlightlyInjuredPatient:\nExpected: T3/Green");
		System.out.println("Found: " + p.getCatInfo());
		System.out.println((p.getCatInfo().equals("T3/Green")) ? "Valid" : "Invalid");
		
		p = new SeverelyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7), 42);
		System.out.println("SeverelyInjuredPatient:\nExpected: T2/Yellow-42");
		System.out.println("Found: " + p.getCatInfo());
		System.out.println((p.getCatInfo().equals("T2/Yellow-42")) ? "Valid" : "Invalid");
		
		p = new EmergencyPatient("Edgar Friendly", LocalTime.of(7, 7));
		System.out.println("EmergencyPatient:\nExpected: T1/Red");
		System.out.println("Found: " + p.getCatInfo());
		System.out.println((p.getCatInfo().equals("T1/Red")) ? "Valid\n" : "Invalid\n");
	}
	
	// Check correct behavior of the compareTo(...) method WITHIN each patient
	// class, i.e. check ordering for each category separately. Use at least
	// three object instances per patient class!
	@Test
	public void validateCompareTo(){
		System.out.println("Validating compareTo methods:\n");
		AbstractPatient p1, p2, p3;
		
		System.out.println("SlightlyInjuredPatient:");
		p1 = new SlightlyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7));
		p2 = new SlightlyInjuredPatient("Olga Dmitrievna", LocalTime.of(7, 8));
		p3 = new SlightlyInjuredPatient("Edward P. Scissors", LocalTime.of(16, 7));
		System.out.println("p1 < p2: " + ((p1.compareTo(p2) > 0) ? "Valid" : "Invalid"));
		System.out.println("p1 < p3: " + ((p1.compareTo(p3) > 0) ? "Valid" : "Invalid"));
		System.out.println("p2 < p3: " + ((p2.compareTo(p3) > 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p2 > p1: " + ((p2.compareTo(p1) < 0) ? "Valid" : "Invalid"));
		System.out.println("p3 > p1: " + ((p3.compareTo(p1) < 0) ? "Valid" : "Invalid"));
		System.out.println("p3 > p2: " + ((p3.compareTo(p2) < 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p1 = p1: " + ((p1.compareTo(p1) == 0) ? "Valid" : "Invalid"));
		System.out.println("p2 = p2: " + ((p2.compareTo(p2) == 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p3 = p3: " + ((p3.compareTo(p3) == 0) ? "Valid\n" : "Invalid\n"));
		
		System.out.println("SeverelyInjuredPatient:");
		p1 = new SeverelyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7), 0);
		p2 = new SeverelyInjuredPatient("Olga Dmitrievna", LocalTime.of(7, 8), 42);
		p3 = new SeverelyInjuredPatient("Edward P. Scissors", LocalTime.of(16, 7), 17);
		System.out.println("p1 < p2: " + ((p1.compareTo(p2) > 0) ? "Valid" : "Invalid"));
		System.out.println("p1 < p3: " + ((p1.compareTo(p3) > 0) ? "Valid" : "Invalid"));
		System.out.println("p2 > p3: " + ((p2.compareTo(p3) < 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p2 > p1: " + ((p2.compareTo(p1) < 0) ? "Valid" : "Invalid"));
		System.out.println("p3 > p1: " + ((p3.compareTo(p1) < 0) ? "Valid" : "Invalid"));
		System.out.println("p3 < p2: " + ((p3.compareTo(p2) > 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p1 = p1: " + ((p1.compareTo(p1) == 0) ? "Valid" : "Invalid"));
		System.out.println("p2 = p2: " + ((p2.compareTo(p2) == 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p3 = p3: " + ((p3.compareTo(p3) == 0) ? "Valid\n" : "Invalid\n"));
		
		System.out.println("EmergencyPatient:");
		p1 = new EmergencyPatient("Edgar Friendly", LocalTime.of(7, 7));
		p2 = new EmergencyPatient("Olga Dmitrievna", LocalTime.of(7, 8));
		p3 = new EmergencyPatient("Edward P. Scissors", LocalTime.of(16, 7));
		System.out.println("p1 < p2: " + ((p1.compareTo(p2) > 0) ? "Valid" : "Invalid"));
		System.out.println("p1 < p3: " + ((p1.compareTo(p3) > 0) ? "Valid" : "Invalid"));
		System.out.println("p2 < p3: " + ((p2.compareTo(p3) > 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p2 > p1: " + ((p2.compareTo(p1) < 0) ? "Valid" : "Invalid"));
		System.out.println("p3 > p1: " + ((p3.compareTo(p1) < 0) ? "Valid" : "Invalid"));
		System.out.println("p3 > p2: " + ((p3.compareTo(p2) < 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p1 = p1: " + ((p1.compareTo(p1) == 0) ? "Valid" : "Invalid"));
		System.out.println("p2 = p2: " + ((p2.compareTo(p2) == 0) ? "Valid\n" : "Invalid\n"));
		System.out.println("p3 = p3: " + ((p3.compareTo(p3) == 0) ? "Valid\n" : "Invalid\n"));
	}

	//**************************************************************
	//********************* Test patient queue *********************
	//**************************************************************
	
	// Check correct behavior of the process method of the patient queue class,
	// i.e. patients are processed in correct order. Initialize test by adding
	// patients in random order. To generate a randomly ordered patient list, 
	// you can use the provided getShuffledArray(AbstractPatient[] patients) method.
	@Test
	public void validateProcessQueue(){
		//TODO Implement test!
	}
	
	
}
