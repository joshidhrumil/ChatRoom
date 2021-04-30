package com.demo.chatRoom.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.RestController;

import com.demo.chatRoom.model.ChatMessageModel;

@RestController
public class Controller {
	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessageModel sendMessage(@Payload ChatMessageModel chatMessageModel)
	{
	     return chatMessageModel;
	}

	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessageModel addUser(@Payload ChatMessageModel chatMessageModel, SimpMessageHeaderAccessor headerAccessor) {
	        
	// Add username in web socket session
	    headerAccessor.getSessionAttributes().put("username", chatMessageModel.getSender());
	        return chatMessageModel;
	    }
}
