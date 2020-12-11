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
public class HacekerService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String TOP_NEWS_URL = "https://hacker-news.firebaseio.com/v0/newstories.json?print=pretty";
	
    public List<Hacker> selectNewstories() {
    	
    	List<Integer> listTopNews = restTemplate.getForObject(TOP_NEWS_URL, List.class);
    	
    	log.info("response = > " + listTopNews.size());
    	
    	int index = 0;
    	List<Hacker> topNewsList = new ArrayList<Hacker>();
    	for ( Integer newsId : listTopNews ) {
    		try {
	    		if (index ++ > 9) {
	    			break;
	    		}
	    		String newsUrl = "https://hacker-news.firebaseio.com/v0/item/"+newsId+".json?print=pretty";
	    		Hacker news = restTemplate.getForObject(newsUrl, Hacker.class);
	    		
	    		topNewsList.add(news);
	    		log.info("select news => {}", news);
    		}catch (Exception e) {
    			log.error("newsId => {} error => {}", newsId, e.getMessage());
			}

    	}
    	
    	return topNewsList;
    }
}
