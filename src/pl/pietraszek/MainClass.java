package pl.pietraszek;

import java.util.ArrayList;
import java.util.List;

import pl.pietraszek.invoke.Invocable;
import pl.pietraszek.invoke.InvokeByDynamicInvocation;
import pl.pietraszek.invoke.InvokeByReflection;
import pl.pietraszek.invoke.InvokeStandard;

/**
 * @author Marcin Pietraszek
 */
public class MainClass {
	
    private static final int DEFAULT_MAX = 1000;
    
	public static void main(String ... args) throws Throwable {
        ExampleClass instance = new ExampleClass();
        int repetitions = parseMax(args);
        
        List<Invocable> invocables = new ArrayList<>();
        invocables.add(new InvokeStandard(instance, repetitions));
        invocables.add(new InvokeByReflection(instance, repetitions));
        invocables.add(new InvokeByDynamicInvocation(instance, repetitions));
        
        for (Invocable invocable : invocables) {
			invocable.invoke();
			invocable.printSummary();
		}
    }

	private static int parseMax(String[] args) {
		if(args != null && args.length > 0) {
			return Integer.parseInt(args[0]);
		}
		return DEFAULT_MAX;
	}
	
}
