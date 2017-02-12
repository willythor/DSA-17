public class MyArrayList {
    private Cow[] elems;
	private int size;

	public MyArrayList() {
		elems = new Cow[10];
		size = 0;
	}

	public MyArrayList(int capacity) {
		elems = new Cow[capacity];
		size = 0;
	}

	public void add(Cow c) {
		if (size == elems.length) {
			Cow[] bigger = new Cow[elems.length*2];
			System.arraycopy(elems, 0, bigger, 0, elems.length);
			elems = bigger;
		}
		elems[size] = c;
		size++;
	}

	public int size() {
		return size;
	}

	public Cow get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return elems[index];
	}

	public Cow remove(int index) {
		if (size <= elems.length/4 && elems.length > 10) {
			Cow[] smaller = new Cow[elems.length/2];
			System.arraycopy(elems,0,smaller,0,elems.length/2);
			elems = smaller;
		}
		Cow element = get(index);
		for (int i=index+1;i<size;i++) {
			elems[i-1] = elems[i];
		}
		size--;
		return element;
	}

//	private void half(){
//		Cow[] smaller = new Cow[elems.length/2];
//		System.arraycopy(elems,0,smaller,0,elems.length/2);
//	}

	public void add(int index, Cow c) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (size == elems.length){
			Cow[] bigger = new Cow[elems.length*2];
			System.arraycopy(elems, 0, bigger, 0, elems.length);
			elems = bigger;
		}
		for (int i = index; i < size; i++) {
			elems[i+1] = elems[i];
		}
		size++;
		elems[index] = c;
	}
}
