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
		if(p == null)
			return -1;					//Non-null elements have higher priority than null.

		if(p.getPriority() == TreatmentPriority.LOW)			//Lower priority
			return -1;
		if(p.getPriority() == TreatmentPriority.HIGH)			//Higher priority
			return 1;
		if(p instanceof SeverelyInjuredPatient){
			int irdiff = (int)Math.signum(((SeverelyInjuredPatient) p).injuryRating - injuryRating);			//If both are severely injured, use injury rating
			if(irdiff == 0){
				return this.arrivalTime.compareTo(p.arrivalTime);
			}
			return irdiff;
		}
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
