package testutil;

public class Employee {
	
	//=====================================================
	
	//Convenience methods for testing
	
	
	//=====================================================	
	
	private int id;
	private String name;

	public Employee(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.getName() + " (" + this.getId() + ")";
	}
	
	//=====================================================
	
	@Override	
	public int hashCode()  {
		int hashCode = 7;
		hashCode = 31 * hashCode + Integer.valueOf(this.id).hashCode();		
		hashCode = 31 * hashCode + (getName()!=null ? getName().hashCode() : 0);
		return hashCode;
	}	

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Employee))
			return false;
		Employee other = (Employee)o;
		return
			this.getId() == other.getId()
				&& safeEquals(this.getName(), other.getName());
	}
	
	public boolean safeEquals(Object a, Object b)  {
		//If only one is nil, then false, use XOR*/
		if (a==null ^ b==null)
			return false;
		//We now know both are null, or not null, check for condition #1 first*/
		//If either is null, they both are
		if (a==null)
			return true;
		//Finally a safe equals
		return a.equals(b);
	}		

	
	
	
}