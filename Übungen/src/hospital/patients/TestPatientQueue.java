package hospital.patients;



import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

import hospital.PatientQueue;
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
		assertTrue("Should be equal", p1.compareTo(p1) == 0);
		assertTrue("Should be equal", p2.compareTo(p2) == 0);
		assertTrue("Should be equal", p3.compareTo(p3) == 0);
		assertTrue("Priority comparison failure", p1.compareTo(p2) < 0);
		assertTrue("Priority comparison failure", p1.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p2.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p2.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p3.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p3.compareTo(p2) > 0);
		
		System.out.println("SeverelyInjuredPatient:");
		p1 = new SeverelyInjuredPatient("Edward P. Scissors", LocalTime.of(7, 8), 42);
		p2 = new SeverelyInjuredPatient("Olga Dmitrievna", LocalTime.of(16, 7), 17);
		p3 = new SeverelyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7), 0);

		assertTrue("Should be equal", p1.compareTo(p1) == 0);
		assertTrue("Should be equal", p2.compareTo(p2) == 0);
		assertTrue("Should be equal", p3.compareTo(p3) == 0);
		assertTrue("Priority comparison failure", p1.compareTo(p2) < 0);
		assertTrue("Priority comparison failure", p1.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p2.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p2.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p3.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p3.compareTo(p2) > 0);
		
		
		System.out.println("EmergencyPatient:");
		p1 = new EmergencyPatient("Edgar Friendly", LocalTime.of(7, 7));
		p2 = new EmergencyPatient("Olga Dmitrievna", LocalTime.of(7, 8));
		p3 = new EmergencyPatient("Edward P. Scissors", LocalTime.of(16, 7));
		assertTrue("Should be equal", p1.compareTo(p1) == 0);
		assertTrue("Should be equal", p2.compareTo(p2) == 0);
		assertTrue("Should be equal", p3.compareTo(p3) == 0);
		assertTrue("Priority comparison failure", p1.compareTo(p2) < 0);
		assertTrue("Priority comparison failure", p1.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p2.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p2.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p3.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p3.compareTo(p2) > 0);
		
		System.out.println("Mixed patients:");
		p1 = new EmergencyPatient("Edgar Friendly", LocalTime.of(7, 7));
		p2 = new SeverelyInjuredPatient("Olga Dmitrievna", LocalTime.of(7, 8), 0);
		p3 = new SlightlyInjuredPatient("Edward P. Scissors", LocalTime.of(16, 7)); 
		assertTrue("Priority comparison failure", p1.compareTo(p2) < 0);
		assertTrue("Priority comparison failure", p1.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p2.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p2.compareTo(p3) < 0);
		assertTrue("Priority comparison failure", p3.compareTo(p1) > 0);
		assertTrue("Priority comparison failure", p3.compareTo(p2) > 0);
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
		System.out.println("Validating PatientQueue.processQueue:\n");
		AbstractPatient[] patients = new AbstractPatient[9];
		patients[0] = new SlightlyInjuredPatient("Edgar Friendly", LocalTime.of(7, 7));
		patients[1] = new SlightlyInjuredPatient("Olga Dmitrievna", LocalTime.of(7, 8));
		patients[2] = new SlightlyInjuredPatient("Edward P. Scissors", LocalTime.of(16, 7));
		
		patients[3] = new SeverelyInjuredPatient("Annegret Mörtel", LocalTime.of(7, 7), 0);
		patients[4] = new SeverelyInjuredPatient("Ricardo Chillychelly", LocalTime.of(7, 8), 42);
		patients[5] = new SeverelyInjuredPatient("Windle Poons", LocalTime.of(16, 7), 0);
		
		patients[6] = new EmergencyPatient("Lothar vom Elsass", LocalTime.of(7, 7));
		patients[7] = new EmergencyPatient("Elsa von Lothringen", LocalTime.of(7, 8));
		patients[8] = new EmergencyPatient("Baron von Münchhausen", LocalTime.of(16, 7));
		
		AbstractPatient[] shufpatients = getShuffledArray(patients);
		PatientQueue<AbstractPatient> queue = new PatientQueue<AbstractPatient>(9);
		for(int i = 0; i < 9; i++){
			//System.out.println(p.toString());
			queue.addPatient(shufpatients[i]);
		}
		
		System.out.println("Processing Queue" + queue.getSize());
		ArrayList<String> data = queue.processQueue();
		System.out.println("Queue Processed " + data.size());
		//assertTrue("Invalid size for data array", data.size() >= 9);
		
		
		for(int i = 0; i < data.size(); i++)
			System.out.println(data.get(i));
		
		assertEquals("Mismatch in queue order!", data.get(0), patients[6].toString());
		assertEquals("Mismatch in queue order!", data.get(1), patients[7].toString());
		assertEquals("Mismatch in queue order!", data.get(2), patients[8].toString());
		assertEquals("Mismatch in queue order!", data.get(3), patients[4].toString());
		assertEquals("Mismatch in queue order!", data.get(4), patients[3].toString());
		assertEquals("Mismatch in queue order!", data.get(5), patients[5].toString());
		assertEquals("Mismatch in queue order!", data.get(6), patients[0].toString());
		assertEquals("Mismatch in queue order!", data.get(7), patients[1].toString());
		assertEquals("Mismatch in queue order!", data.get(8), patients[2].toString());
		
	}
	
	
}
