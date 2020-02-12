package kr.or.ddit.behavioral.iterator;

public class NameRepository implements Container {
	
	public String names[] = {"도도", "레레", "미미", "파파", "뺘!"};
	
	@Override
	public Iterator getIterator() {
		return new NameIterator();
	}
	
	private class NameIterator implements Iterator{
		
		int index;
		
		@Override
		public boolean hasNext() {
			if(index < names.length) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (this.hasNext()) {
				return names[index++];
			}
			return null;
		}
		
	}
}
