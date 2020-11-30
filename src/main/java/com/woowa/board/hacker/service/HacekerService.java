package com.woowa.board.hacker.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.woowa.board.hacker.vo.Hacker;

@Service
public class HacekerService {

	private static final int String = 0;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private RestTemplate restTemplate;
	
	private static final String TOP_NEWS_URL = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
	
    @Autowired
    public HacekerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public List<Hacker> selectNewstories() {
    	
    	List<Integer> listTopNews = restTemplate.getForObject(TOP_NEWS_URL, List.class);
    	
    	logger.info("response = > " + listTopNews.size());
    	
    	int index = 0;
    	List<Hacker> topNewsList = new ArrayList<Hacker>();
    	for ( Integer newsId : listTopNews ) {
    		if (index ++ > 9) {
    			break;
    		}
    		String newsUrl = "https://hacker-news.firebaseio.com/v0/item/"+newsId+".json?print=pretty";
    		Hacker news = restTemplate.getForObject(newsUrl, Hacker.class);
    		
    		topNewsList.add(news);
    		logger.info("news => " + news);

    	}
    	
    	return topNewsList;
    }
}
