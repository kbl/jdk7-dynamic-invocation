package pl.pietraszek.invoke;

import pl.pietraszek.ExampleClass;

/**
 * @author Marcin Pietraszek
 */
public abstract class InvokeBase implements Invocable {
	
	protected ExampleClass instance;
	protected int repetitions;
	
	private long executionStartTime;
	private long executionTime;
	
	public InvokeBase(ExampleClass instance, int repetitions) {
		this.instance = instance;
		this.repetitions = repetitions;
	}
	
	@Override
	public void printSummary() {
		System.out.println(getClass());
		System.out.println("execution time: " + executionTime);
	}
	
	@Override
	public void invoke() {
		startCountingTime();
		invokeMethod();
		countExecutionTime();
	}
	
	protected abstract void invokeMethod();

	private void countExecutionTime() {
		executionTime = System.currentTimeMillis() - executionStartTime;
	}

	private void startCountingTime() {
		executionStartTime = System.currentTimeMillis();
	}

}
