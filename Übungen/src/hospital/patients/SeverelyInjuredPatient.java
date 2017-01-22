package hospital.patients;

import java.time.LocalTime;

public class SeverelyInjuredPatient extends AbstractPatient {
	
	protected int injuryRating;

	public SeverelyInjuredPatient(String patientName,
			LocalTime patientArrivalTime, int injuryRating) {
		super(patientName, patientArrivalTime);
		this.injuryRating = injuryRating;
	}

	@Override
	public int compareTo(AbstractPatient p) {
		if(p.getPriority() == TreatmentPriority.LOW)			//Lower priority
			return -1;
		if(p.getPriority() == TreatmentPriority.HIGH)			//Higher priority
			return 1;
		if(p instanceof SeverelyInjuredPatient)
			return (this.injuryRating > ((SeverelyInjuredPatient)p).injuryRating)? -1 : 1;			//If both are severely injured, use injury rating
		
		return 0;
	}

	@Override
	public TreatmentPriority getPriority() {
		return TreatmentPriority.MEDIUM;
	}

	@Override
	public String getCatInfo() {
		return "T2/Yellow-" + injuryRating;
	}

}
