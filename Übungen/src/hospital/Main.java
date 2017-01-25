package hospital;

import hospital.patients.TestPatientQueue;

public class Main {

	public static void main(String[] args) {
		TestPatientQueue tester = new TestPatientQueue();
		tester.validateGetCatInfo();
		tester.validateGetPriority();
		tester.validateCompareTo();
		tester.validateProcessQueue();
	}

}
