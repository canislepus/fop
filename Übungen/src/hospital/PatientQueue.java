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
	public void addPatient(AbstractPatient patient) {
		this.push(patient);
	}
	
	/**
	 * Flushes the list and returns all patients' information.
	 * @return The patient information as an ArrayList of Strings
	 */
	public ArrayList<String> processQueue(){
		ArrayList<String> queueInfo = new ArrayList<String>(this.getSize());		//There are as many Strings as patients.
		for (int i = 0; i <= this.getSize(); i++){
			queueInfo.set(i, this.pop().toString());
		}
		return queueInfo;
	}

}
