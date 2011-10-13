package pl.pietraszek;


public class BasicExampleClass {

    private String examplePrivateField;
    protected String exampleProtectedField;
    /* package */ String exampleDefaultField;
    public String examplePublicField;

    public BasicExampleClass() {
        this.examplePrivateField = "private field";
        this.exampleProtectedField = "protected field";
        this.exampleDefaultField = "default field";
        this.examplePublicField = "public field";
    }

}
