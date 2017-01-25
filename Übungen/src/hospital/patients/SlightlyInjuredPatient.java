package hospital.patients;

import java.time.LocalTime;

public class SlightlyInjuredPatient extends AbstractPatient {

	public SlightlyInjuredPatient(String patientName, LocalTime patientArrivalTime) {
		super(patientName, patientArrivalTime);
	}

	@Override
	public int compareTo(AbstractPatient p) {
		if(p == null)
			return -1;					//Non-null elements have higher priority than null.

		if(p.getPriority() != TreatmentPriority.LOW)
			return 1;
		
		return this.arrivalTime.compareTo(p.arrivalTime);
	}

	@Override
	public TreatmentPriority getPriority() {
		return TreatmentPriority.LOW;
	}

	@Override
	public String getCatInfo() {
		return "T3/Green";
	}

}
