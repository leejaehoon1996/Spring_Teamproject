package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.service.MessageService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class MessageController {
	private MessageService messageService;
	
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@GetMapping("/message")
	public String message(Model model, @RequestParam(value="page", defaultValue="1") Integer pageNum) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		
		List<MessageDTO> messageDto = messageService.getReceiveList(currentUsername, pageNum);
		Integer[] pageList = messageService.getPageList(pageNum);
		
		model.addAttribute("message", messageDto);
		model.addAttribute("pageList", pageList);
		return "message";
	}
	
	@GetMapping("/sendMessage")
	public String sendMessage(Model model, @RequestParam(value="page", defaultValue="1")Integer pageNum) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentUsername = authentication.getName();
		List<MessageDTO> messageDto = messageService.getSendList(currentUsername, pageNum);
		Integer[] pageList = messageService.getPageList(pageNum);
		
		model.addAttribute("message", messageDto);
		model.addAttribute("pageList", pageList);
		return "sendMessage";
	}
	
	@GetMapping("/sendmsg")
	public String sendmsg() {
		return "sendmsg";
	}
	
	@PostMapping("/sendmsg")
	public String send(MessageDTO msgDTO) {
		messageService.saveMessage(msgDTO);
		
		return "redirect:/message";
	}
	
	@GetMapping("/deleteMessage/{no}")
	public String deleteMessage(@PathVariable("no") Long idx, Model model) { 
		messageService.deleteMessage(idx);
		  
		
		return "redirect:/message"; 
	 }
	
	@GetMapping("/deleteSendMessage/{no}")
	public String deleteSendMessage(@PathVariable("no") Long idx, Model model) { 
		messageService.deleteSendMessage(idx);
		  
		
		return "redirect:/sendMessage"; 
	 }
	
}
