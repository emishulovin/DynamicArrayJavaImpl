package testutil;

/*
 * Thing
 * Purpose: To allow construction of simple objects for 
 * 			
 * 
 * testing. Next level up from testing 
 * 			primitive-like objects (e.g. String, Integer, etc)
 */

public class Thing {
	
	//=====================================================
	
	//Convenience methods for testing
		
	//=====================================================	
	
	private int id;
	
	public Thing(int id) {
		this.id = id;
	}	
	
	//=====================================================	
	
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("(%s)", this.id); 
	}
	
	//=====================================================
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Thing other = (Thing) obj;
		if (id != other.id)
			return false;
		return true;
	}

}