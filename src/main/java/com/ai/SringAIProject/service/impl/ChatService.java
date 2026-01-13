package com.ai.SringAIProject.service.impl;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ai.SringAIProject.service.*;


@Service
public class ChatService implements IChatService{
	
	
	private final ChatModel chatModel;
	
//	@Autowired
	public ChatService (ChatModel chatModel) {
		this.chatModel= chatModel;
	}
	
	@Override
	public String getResponse(String prompt) {
		return chatModel.call(prompt);
	}
	
	@Override
	public String getResponseOptions(String promptMsg) {
		ChatResponse chatResp = chatModel.call(new Prompt(
				promptMsg,
				OpenAiChatOptions.builder()
				.model("gpt-3.5-turbo")
				.temperature(0.4)
				.build()
				));
		if (chatResp != null && chatResp.getResult() != null) {
	        return chatResp.getResult().getOutput().getText();
	    }
	    return "Error: Empty response from AI";
	}

}
