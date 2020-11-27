package com.woowa.board.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

//	@Autowired
//	private TestEntity dao;
	
	@RequestMapping("/index")
	public String index(Model model) {
		
		model.addAttribute("test", "이원진");
		
		return "index";
	}
}


//@Controller
//@RequestMapping(value = "/")
//public class HomeController {
//
//    @GetMapping(value = "/")
//    public ModelAndView home() {
//        ModelAndView modelAndView = new ModelAndView();
//        
//        modelAndView.setViewName("home");
//        
//        Map<String, Object> map = new HashMap<>();
//        map.put("name", "Bamdule");
//        map.put("date", LocalDateTime.now());
//        
//        modelAndView.addObject("data", map);
//        
//        return modelAndView;
//    }
//}