package com.developer.sunmaungoo.ref;

public class SearchData {
	
	private boolean isFound;
	
	private int foundIndex;

	public SearchData(boolean isFound, int foundIndex) {
		this.isFound = isFound;

		this.foundIndex = foundIndex;
	}

	public boolean isFound() {
		return this.isFound;
	}

	public int getFoundIndex() {
		return this.foundIndex;
	}
}
