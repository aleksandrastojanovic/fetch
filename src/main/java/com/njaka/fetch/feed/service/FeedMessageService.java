package com.njaka.fetch.feed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.njaka.fetch.entity.FeedMessage;

public interface FeedMessageService {

	public Page<FeedMessage> findAll(Pageable pageable);

	public FeedMessage find(int id);

//	public void save(FeedMessage feedMessage);
//
//	public void save(String channel);

	public void deleteById(int id);

}
