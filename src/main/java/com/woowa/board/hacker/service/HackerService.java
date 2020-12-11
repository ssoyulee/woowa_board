package com.woowa.board.hacker.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.woowa.board.hacker.vo.Hacker;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HackerService {
	
	private static final String TOP_NEWS_URL = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
	
	@Autowired
	private RestTemplate restTemplate;
	
    @SuppressWarnings("unchecked")
	public List<Hacker> selectNewstories() throws Exception {

		// STEP1. 최신 뉴스를 가지고 온다.
		List<Integer> listTopNews = restTemplate.getForObject(TOP_NEWS_URL, List.class);
		log.info("selectNewstories ::: listTopNews = > " + listTopNews.size());

		// STEP2. 최신 뉴스 10개만을 가지고 온다.
    	List<Hacker> listTop10News = new ArrayList<Hacker>();
    	listTopNews.stream().limit(10).forEach(
			(newsId)-> {
	    		String url = "https://hacker-news.firebaseio.com/v0/item/"+newsId+".json?print=pretty";
	    		Hacker news = restTemplate.getForObject(url, Hacker.class);
	    		listTop10News.add(news);
			}
    	);
    	log.info("selectNewstories ::: listTop10News = > " + listTop10News.size());
    	
    	return listTop10News;
    }
}
