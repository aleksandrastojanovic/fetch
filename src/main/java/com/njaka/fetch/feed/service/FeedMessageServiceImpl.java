package com.njaka.fetch.feed.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.njaka.fetch.entity.FeedMessage;
import com.njaka.fetch.feed.dao.FeedMessageRepository;

@Service
public class FeedMessageServiceImpl implements FeedMessageService {

	FeedMessageRepository feedMessageRepository;

	@Autowired
	public FeedMessageServiceImpl(FeedMessageRepository feedMessageRepository) {
		this.feedMessageRepository = feedMessageRepository;
	}

	public FeedMessage find(int id) {
		Optional<FeedMessage> result = feedMessageRepository.findById(id);

		FeedMessage feedMessage = null;

		if (result.isPresent()) {
			feedMessage = result.get();
		} else {
			throw new RuntimeException("Feed id not found: " + id);
		}
		return feedMessage;
	}

	public void save(FeedMessage feedMessage) {
		feedMessageRepository.save(feedMessage);

	}

//	public void save(String channel) {
//		String url = getUrl(channel);
//		RSSFeedParser rssFeedParser = new RSSFeedParser(url);
//		Feed feed = rssFeedParser.readFeed();
//		List<FeedMessage> messages = feed.getMessages();
//		for (FeedMessage message : messages) {
//			if (feedMessageRepository.findByGuid(message.getGuid()) == null) {
//				feedMessageRepository.save(message);
//			}
//		}
//	}

	public void deleteById(int id) {
		feedMessageRepository.deleteById(id);

	}

//	private String getUrl(String channel) {
//		String url = "";
//		switch (channel) {
//		case "n1":
//			url = "https://rs-8nqof7qzeod2et99kimwqegbnmsmjnby.n1info.com/feed/";
//			break;
//		case "blic":
//			url = "https://www.blic.rs/rss/danasnje-vesti";
//			break;
//		default:
//			break;
//		}
//		return url;
//	}

	@Override
	public Page<FeedMessage> findAll(Pageable pageable) {
		return feedMessageRepository.findAll(pageable);
	}

}
