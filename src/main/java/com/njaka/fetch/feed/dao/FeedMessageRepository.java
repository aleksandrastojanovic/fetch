package com.njaka.fetch.feed.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.njaka.fetch.entity.FeedMessage;

public interface FeedMessageRepository extends JpaRepository<FeedMessage, Integer>{

}
