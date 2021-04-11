package com.njaka.fetch.feed.service;

import java.util.List;

import com.njaka.fetch.entity.FeedMessage;

public interface FeedMessageService {

	public List<FeedMessage> findAll();

	public FeedMessage find(int id);

	public void save(FeedMessage feedMessage);
	
	public void save(String channel);

	public void deleteById(int id);
}
