package pl.pietraszek;

import java.lang.reflect.Field;

import javax.management.RuntimeErrorException;

/**
 * @author Marcin Pietraszek
 */
public class MainClass {

    public static void main(String ... args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        BasicExampleClass example = new BasicExampleClass();
        
        checkPrivateFieldValue(example);
    }

	private static void checkPrivateFieldValue(BasicExampleClass example) {
		try {
			Field field = example.getClass().getDeclaredField("examplePrivateField");
			field.setAccessible(true);
			String privateFieldValue = (String) field.get(example);
			field.setAccessible(false);
			System.out.println(privateFieldValue);
			assert "private field".equals(privateFieldValue);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
