package com.example.wordpuzzle.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Utility class to find the list of words in a given source
 * 
 * @author shankari.sakthivel
 *
 */
public class WordFinder {

	/**
	 * load dictionary from a file
	 * 
	 * @return
	 */
	private static List<String> getDict() {
		try {
			List<String> dict = Files.lines(Paths.get("dict/dict.txt")).collect(Collectors.toList());
			return dict;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * traverse in the top and botom direction
	 * 
	 * @param inputBlock
	 * @param row
	 * @param col
	 * @param dict
	 * @param rowSize
	 * @param colSize
	 * @return
	 */
	private static Set<String> moveBottmtoTop(char[][] inputBlock, int row, int col, List<String> dict, int rowSize,
			int colSize) {
		Set<String> results = new HashSet<String>();
		StringBuilder word = new StringBuilder();
		for (int i = row; i >= 0; i--) {
			word.append(inputBlock[i][col]);
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
		}
		return results;
	}

	/**
	 * traverse in the left and right direction
	 * 
	 * @param inputBlock
	 * @param row
	 * @param col
	 * @param dict
	 * @param dimension
	 * @param colSize
	 * @return
	 */
	private static Set<String> moveLefttoRight(char[][] inputBlock, int row, int col, List<String> dict, int dimension,
			int colSize) {
		Set<String> results = new HashSet<String>();
		StringBuilder word = new StringBuilder();
		for (int j = col; j >= 0; j--) {
			word.append(inputBlock[row][j]);
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
		}
		return results;
	}

	/**
	 * traverses diagonally for the given indexes
	 * 
	 * @param inputBlock
	 * @param row
	 * @param col
	 * @param dict
	 * @param rowSize
	 * @param colSize
	 * @return
	 */
	private static Set<String> moveDiagonal(char[][] inputBlock, int row, int col, List<String> dict, int rowSize,
			int colSize) {
		Set<String> results = new HashSet<String>();
		StringBuilder word = new StringBuilder();
		for (int i = row, j = col; i < rowSize && j < colSize; i++, j++) {
			word.append(inputBlock[i][j]);
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
		}
		return results;
	}

	/**
	 * traverse reverse diagonally for a given index
	 * 
	 * @param inputBlock
	 * @param row
	 * @param col
	 * @param dict
	 * @param rowSize
	 * @param colSize
	 * @return
	 */
	private static Set<String> moveReverseDiagonal(char[][] inputBlock, int row, int col, List<String> dict,
			int rowSize, int colSize) {
		Set<String> results = new HashSet<String>();
		StringBuilder word = new StringBuilder();
		for (int i = row, j = col; i < rowSize && j >= 0; i++, j--) {
			word.append(inputBlock[i][j]);
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
			results.addAll(dict.stream().filter(w -> word.toString().equalsIgnoreCase(w)).collect(Collectors.toSet()));
			word.reverse();
		}
		return results;
	}

	/**
	 * finds the list of words available in given source
	 * 
	 * @param inputBlock
	 * @param rowSize
	 * @param colSize
	 * @return
	 */
	public static Set<String> findWord(char[][] inputBlock, int rowSize, int colSize) {
		Set<String> results = new HashSet<String>();
		List<String> dict = getDict();
		// traverse the entire source with all directions and starts from given cell
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < colSize; j++) {
				results.addAll(moveLefttoRight(inputBlock, i, j, dict, rowSize, colSize));
				results.addAll(moveBottmtoTop(inputBlock, i, j, dict, rowSize, colSize));
				results.addAll(moveDiagonal(inputBlock, i, j, dict, rowSize, colSize));
				results.addAll(moveReverseDiagonal(inputBlock, i, j, dict, rowSize, colSize));
			}
		}
		return results;
	}
}
