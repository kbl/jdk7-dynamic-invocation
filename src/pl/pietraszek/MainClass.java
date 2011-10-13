package pl.pietraszek;

import java.lang.reflect.Field;

/**
 * @author Marcin Pietraszek
 */
public class MainClass {

    private ExampleClass example;

	public MainClass(ExampleClass example) {
    	this.example = example;
	}

	public static void main(String ... args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        ExampleClass example = new ExampleClass();
        
        MainClass mainClass = new MainClass(example);
        System.out.println(mainClass.checkPrivateFieldValue());
    }

	private String checkPrivateFieldValue() {
		try {
			Field field = example.getClass().getDeclaredField("examplePrivateField");
			
			field.setAccessible(true);
			String privateFieldValue = (String) field.get(example);
			field.setAccessible(false);
			
			return privateFieldValue;
		} catch (IllegalArgumentException | IllegalAccessException | 
				 NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
