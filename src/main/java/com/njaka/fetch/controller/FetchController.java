package com.njaka.fetch.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
	public String allNews(HttpServletRequest request, Model model) {

		int page = 0; // default page number is 0 
		int size = 9; // default page size is 10

		if (request.getParameter("page") != null
				&& !request.getParameter("page").isEmpty()) {
			page = Integer.parseInt(request.getParameter("page")) - 1;
		}

		if (request.getParameter("size") != null
				&& !request.getParameter("size").isEmpty()) {
			size = Integer.parseInt(request.getParameter("size"));
		}

		model.addAttribute("feedMessages",
				feedMessageService.findAll(PageRequest.of(page, size)));
		return "/feed/home";
	}

	@GetMapping("/feedMessage")
	public String news(@RequestParam int id, Model model) {
		FeedMessage feedMessage = feedMessageService.find(id);
		model.addAttribute("feedMessage", feedMessage);
		return "/feed/feedMessage";
	}
}
