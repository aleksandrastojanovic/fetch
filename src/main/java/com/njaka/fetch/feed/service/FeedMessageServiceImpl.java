package com.njaka.fetch.feed.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<FeedMessage> findAll() {

		return feedMessageRepository.findAll();
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

	public void deleteById(int id) {
		feedMessageRepository.deleteById(id);

	}

}
