package com.njaka.fetch.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.njaka.fetch.entity.Feed;
import com.njaka.fetch.entity.FeedMessage;
import com.njaka.fetch.feed.service.FeedMessageService;
import com.njaka.fetch.feed.service.RSSFeedParser;

@RestController
@RequestMapping(value = "/")
public class FetchController {

	private Feed feed;
	private RSSFeedParser rssFeedParser;
	private FeedMessageService feedMessageService;

	@Autowired
	public FetchController(FeedMessageService feedMessageService) {
		this.feedMessageService = feedMessageService;
	}

	// Proba - direktno cita i prikazuje, bez snimanja u bazu
	@RequestMapping(value = "/proba", method = RequestMethod.GET)
	public List<FeedMessage> fetch(@RequestParam String channel) {
		String url = getUrl(channel);
		rssFeedParser = new RSSFeedParser(url);
		feed = rssFeedParser.readFeed();
		return feed.getMessages();
	}

	// Read RSS Feed and save to db
	@RequestMapping(value = "/saveFeed", method = RequestMethod.GET)
	public void saveFeed(@RequestParam String channel) {
		String url = getUrl(channel);
		rssFeedParser = new RSSFeedParser(url);
		feed = rssFeedParser.readFeed();
		List<FeedMessage> messages = feed.getMessages();
		for (FeedMessage message : messages) {
			feedMessageService.save(message);
		}

	}

	// Retrieve feed messages from db
	@RequestMapping(value = "/getFeed", method = RequestMethod.GET)
	public List<FeedMessage> getFeed() {
		return feedMessageService.findAll();
	}

	private String getUrl(String channel) {
		String url = "";
		switch (channel) {
		case "n1":
			url = "https://rs-8nqof7qzeod2et99kimwqegbnmsmjnby.n1info.com/feed/";
			break;
		case "blic":
			url = "https://www.blic.rs/rss/danasnje-vesti";
			break;
		default:
			break;
		}
		return url;
	}
}
