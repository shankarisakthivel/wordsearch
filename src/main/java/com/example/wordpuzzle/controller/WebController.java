package com.example.wordpuzzle.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.wordpuzzle.model.WordSource;
import com.example.wordpuzzle.util.WordFinder;

/**
 * controller class to handle requests
 * 
 * @author shankari.sakthivel
 *
 */
@Controller
public class WebController {

	/**
	 * home page mapping
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/", "/index" })
	public String index(Model model) {
		WordSource source = new WordSource();
		model.addAttribute("wordSource", source);
		return "index";
	}

	/**
	 * Process words in a given block
	 * 
	 * @param wordSource
	 * @param model
	 * @return
	 */
	@PostMapping("/processWords")
	public String process(@ModelAttribute("wordSource") WordSource wordSource, Model model) {
		char[][] inputBlock = formInputBlock(wordSource);
		Set<String> wordSet = WordFinder.findWord(inputBlock, wordSource.getRowSize(), wordSource.getColSize());
		writeToFile(wordSet);
		model.addAttribute("message", "Written to File \"output\\words.txt\" Successfully");
		model.addAttribute("words", String.join(",", wordSet));
		return "index";
	}

	/**
	 * create input block
	 * 
	 * @param wordSource
	 * @return
	 */
	private char[][] formInputBlock(WordSource wordSource) {
		char[][] input = new char[wordSource.getRowSize()][wordSource.getColSize()];
		int wordIndex = 0;
		for (int i = 0; i < wordSource.getRowSize() && wordIndex < wordSource.getWord().length(); i++) {
			for (int j = 0; j < wordSource.getColSize() && wordIndex < wordSource.getWord().length(); j++) {
				input[i][j] = wordSource.getWord().charAt(wordIndex++);
			}
		}
		return input;
	}

	/**
	 * writing to a file
	 * 
	 * @param wordSet
	 */
	private static void writeToFile(Set<String> wordSet) {
		Path path = Paths.get("output/words.txt");
		try {
			Files.write(path, wordSet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
