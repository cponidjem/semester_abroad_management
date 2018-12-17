package beans;

public enum Role {
	student("student"),
	insa("insa"),
	university("university");
	
	private String name;

    Role(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

}
