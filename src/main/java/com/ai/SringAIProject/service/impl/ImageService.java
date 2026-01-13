package com.ai.SringAIProject.service.impl;


import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.SringAIProject.service.IImageService;

@Service
public class ImageService implements IImageService{
	
	private final OpenAiImageModel openAiImageModel ;
	
//	@Autowired
	public ImageService(OpenAiImageModel openAiImageModel ) {
		this.openAiImageModel = openAiImageModel;
	}
	
	@Override
	public ImageResponse generateImage(String prompt) {
		
		ImageResponse iResp = openAiImageModel.call(new ImagePrompt(prompt));
		return iResp;
	}

	@Override
	public ImageResponse generateImageOpitons(String prompt) {
		ImageResponse iResp = openAiImageModel.call(
				new ImagePrompt(prompt,
						OpenAiImageOptions.builder()
						.model("dall-e-3")
						.quality("hd")
						.N(1)
						.height(1024)
						.width(1024)
						.build()		
						));
		return iResp;
	}

	@Override
	public ImageResponse generateImageOptionsUserInput(String prompt, String quality, int n, int width, int height) {
		ImageResponse iResp = openAiImageModel.call(
				new ImagePrompt(prompt,
				OpenAiImageOptions.builder()
				.quality(quality)
				.N(n)
				.width(width)
				.height(height)
				.build())); 
		return iResp;
	}
	
}
