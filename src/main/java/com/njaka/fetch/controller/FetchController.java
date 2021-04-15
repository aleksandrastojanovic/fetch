package com.njaka.fetch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.njaka.fetch.entity.FeedMessage;
import com.njaka.fetch.feed.service.FeedMessageService;

@Controller
@RequestMapping("/feed")
public class FetchController {

	private FeedMessageService feedMessageService;

	@Autowired
	public FetchController(FeedMessageService feedMessageService) {
		this.feedMessageService = feedMessageService;
	}
	
	@GetMapping("/home")
	public String allNews(Model model){
		List<FeedMessage> feedMessages = feedMessageService.findAll();
		model.addAttribute("feedMessages",feedMessages);
		return "/feed/home";
	}
	
	@GetMapping("/feedMessage")
	public String news(@RequestParam int id, Model model){
		FeedMessage feedMessage = feedMessageService.find(id);
		model.addAttribute("feedMessage",feedMessage);
		return "/feed/feedMessage";
	}
}
