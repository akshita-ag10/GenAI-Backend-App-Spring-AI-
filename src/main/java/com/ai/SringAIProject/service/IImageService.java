package com.ai.SringAIProject.service;

import org.springframework.ai.image.ImageResponse;

public interface IImageService {
	
	public ImageResponse generateImage(String prompt);
	public ImageResponse generateImageOpitons(String prompt);
	public ImageResponse generateImageOptionsUserInput(String prompt, String quality, int n, int width, int height);

}
