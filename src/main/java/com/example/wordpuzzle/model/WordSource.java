package com.example.wordpuzzle.model;
/**
 * Model class to hold inputs
 * @author shankari.sakthivel
 *
 */
public class WordSource {

	private int rowSize;
	private int colSize;
	private String word;


	public int getRowSize() {
		return rowSize;
	}

	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	public int getColSize() {
		return colSize;
	}

	public void setColSize(int colSize) {
		this.colSize = colSize;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}
}
