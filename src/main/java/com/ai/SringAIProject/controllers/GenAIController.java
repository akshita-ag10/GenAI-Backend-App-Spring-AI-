package com.ai.SringAIProject.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.SringAIProject.service.IChatService;
import com.ai.SringAIProject.service.IImageService;
import com.ai.SringAIProject.service.IRecipeService;
import com.ai.SringAIProject.service.impl.ChatService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class GenAIController {
	
	@Autowired
	private IChatService chatService;	
	@Autowired
	private IImageService imageService;	
	@Autowired
	private IRecipeService recipeService;
	
	@GetMapping("/ask-ai") //     http://localhost:8080/ask-ai?prompt=What is the temperature in delhi today
	public ResponseEntity<String> getResponse(@RequestParam String prompt){
		String resp = chatService.getResponse(prompt);
		return new ResponseEntity<String> (resp, HttpStatus.OK);
	}
	
	
	@GetMapping("/ask-ai-options")
	public ResponseEntity<String> getResponseOptions(@RequestParam String prompt){
		String resp = chatService.getResponseOptions(prompt);
		return new ResponseEntity<String> (resp, HttpStatus.OK);
	}
	
	@GetMapping("/gen-image")
	public void getImage(HttpServletResponse response, @RequestParam String prompt) throws IOException{
		ImageResponse iResp = imageService.generateImage(prompt);
		String imgUrl = iResp.getResult().getOutput().getUrl();
		response.sendRedirect(imgUrl);
	}
	
	@GetMapping("/gen-image-options")
	public List<String> getImageOpitons(@RequestParam String prompt){
		ImageResponse iResp = imageService.generateImageOpitons(prompt);
		List<String> imgUrls = iResp.getResults().stream().map(result->result.getOutput().getUrl()).toList();
		return imgUrls;
	}
	
	//to take user input for opitons
	@GetMapping("/gen-image-options-user-input")
	public List<String>getImageOptionsUserInput(@RequestParam String prompt,
										@RequestParam (defaultValue="hd") String quality,
										@RequestParam (defaultValue="1")  int n,
										@RequestParam (defaultValue="1024") int width,
										@RequestParam (defaultValue="1024")  int height){
		
		ImageResponse iResp = imageService.generateImageOptionsUserInput(prompt, quality, n, width, height);
		List<String> imgUrls = iResp.getResults().stream().map(result -> result.getOutput().getUrl()).toList();
		return imgUrls;
	}
	
	@GetMapping("/recipe-creator")
	public String recipeCreator (@RequestParam String ingredients,
								  @RequestParam (defaultValue="any") String cuisine,
								  @RequestParam (defaultValue="") String dietaryRestrictions) {
		
		return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions);
		
	}

}
