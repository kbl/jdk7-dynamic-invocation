package pl.pietraszek.invoke;

import pl.pietraszek.ExampleClass;

/**
 * @author Marcin Pietraszek
 */
public class InvokeStandard extends InvokeBase {

	public InvokeStandard(ExampleClass instance, int repetitions) {
		super(instance, repetitions);
	}

	@Override
	protected void invokeMethod() {
		for(int i = 0; i < repetitions; i++) {
			instance.exampleMethod(i);
		}
	}

}
