package gz.itcast.entity;

public class Types {
	private int id;
	private String name;

	
	
	public Types(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Types() {
	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
