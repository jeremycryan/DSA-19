public class MyArrayList {
	
	public static void main(String[] args) {
		MyArrayList a = new MyArrayList(2);
		a.add(new Cow("John", 3, "Yellow"));
		a.add(new Cow("Quincy", 900, "Green"));
		a.remove(0);		
		a.add(new Cow("Bart", 2, "Orange"));
		a.add(new Cow("Mindsey", 0, "Chartreuse"));
		a.add(1, new Cow("Lanzo", 20, "Toasted Corn"));
	}
	
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
    	
    	// With no capacity argument, initialize cow array with capacity 10.
        elems = new Cow[10];
        size = 0;
        
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
    	
    	// Initialize the cow array with size capacity
        elems = new Cow[capacity];
        size = 0;
        
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
    	
    	// Resize array if adding a cow would otherwise overflow
    	if (size >= elems.length) {
    		Cow[] new_elems;
    		new_elems = new Cow[elems.length*2];
    		System.arraycopy(elems, 0, new_elems, 0, elems.length);
    		elems = new_elems;
    	}
    	
    	// Add a cow to the end of the list and increment size.
        elems[size] = c;
        size += 1;

        System.out.printf("APPEND ");
    	for (int i = 0; i < size; i += 1) {
    		System.out.printf("%s, ", elems[i]);
    	}
    	System.out.printf("\n");
       
    }

    // TODO: Runtime: O(1)
    public int size() {
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if (index < size && index >= 0) {
        	
        	// Return cow at specified index
        	return elems[index];
        	
        } else {
        	throw new IndexOutOfBoundsException("There ain't no cows there, fellah.");
        }
    }

    // TODO: Runtime: O(N)
    // Can be decreased to O(1) if maintaining order of array doesn't matter.
    public Cow remove(int index) {
        if (index < size && index >= 0) {
        	
        	// Identify cow to remove
        	Cow return_cow = elems[index];
        	
        	// Move each other cow in the array forward one spot and decrement size
        	for (int i = index; i < size - 1; i += 1) {
        		elems[i] = elems[i+1];
        	}
        	
        	size -= 1;
        	
        	// Reduce size of array if less than a quarter is used
        	if (size <= elems.length / 4) {
        		Cow[] new_elems;
        		new_elems = new Cow[elems.length/2];
        		System.arraycopy(elems, 0, new_elems, 0, elems.length/4);
        	}
        	
        	// Return removed cow, print cow list
        	System.out.printf("REMOVE ");
        	for (int i = 0; i < size; i += 1) {
        		System.out.printf("%s, ", elems[i]);
        	}
        	System.out.printf("\n");
        	return return_cow;
        	
        } else {
        	throw new IndexOutOfBoundsException("Ain't none there, neither.");
        }
    }

    // TODO: Runtime: O(N)
    public void add(int index, Cow c) {
        if (index <= size && index >= 0) {
        	
        	// Resize array if adding a cow would otherwise overflow
        	if (size >= elems.length) {
        		Cow[] new_elems;
        		new_elems = new Cow[elems.length*2];
        		System.arraycopy(elems, 0, new_elems, 0, elems.length);
        		elems = new_elems;
        	}
        	
        	// Move all array elements after index back a spot
        	for (int i = size - 1; i >= index; i -= 1) {
        		elems[i+1] = elems[i];
        	}
        	
        	// Increment size and add new cow
        	size += 1;
        	elems[index] = c;
        	
        	// Print current cow list
        	System.out.printf("INSERT ");
        	for (int i = 0; i < size; i += 1) {
        		System.out.printf("%s, ", elems[i]);
        	}
        	System.out.printf("\n");
        	
        } else {
        	throw new IndexOutOfBoundsException("Where ya think ya puttin' that?");
        }
    }
}