package com.njaka.fetch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.njaka.fetch.entity.FeedMessage;
import com.njaka.fetch.feed.service.FeedMessageService;

@RestController
@RequestMapping(value = "/")
public class FetchRestController {

	private FeedMessageService feedMessageService;

	@Autowired
	public FetchRestController(FeedMessageService feedMessageService) {
		this.feedMessageService = feedMessageService;
	}

	// Read RSS Feed and save to db
	@RequestMapping(value = "/saveFeed", method = RequestMethod.GET)
	public void saveFeed(@RequestParam String channel) {
		feedMessageService.save(channel);

	}

	// Retrieve feed messages from db
	@RequestMapping(value = "/getFeed", method = RequestMethod.GET)
	public Page<FeedMessage> getFeed() {
		return feedMessageService.findPaginated(1, 18);
	}

}
