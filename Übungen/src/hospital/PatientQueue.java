package hospital;

import java.util.ArrayList;

import hospital.heap.ArrayListHeap;
import hospital.patients.*;

public class PatientQueue<T extends AbstractPatient> extends ArrayListHeap<AbstractPatient>{

	public PatientQueue(int initialSize) {
		super(initialSize);
	}
	
	/**
	 * Adds a patient to the queue.
	 * @param patient The patient to be added
	 */
	public void addPatient(T patient) {
		this.push(patient);
	}
	
	/**
	 * Flushes the list and returns all patients' information.
	 * @return The patient information as an ArrayList of Strings
	 */
	public ArrayList<String> processQueue(){
		ArrayList<String> queueInfo = new ArrayList<String>(this.getSize());		//There are as many Strings as patients.
		System.out.println(this.getSize());
		while (!isEmpty()){
			AbstractPatient nextpatient = this.pop();
			if(nextpatient != null){
				queueInfo.add(nextpatient.toString());
			}
			
		}
		return queueInfo;
	}

}
