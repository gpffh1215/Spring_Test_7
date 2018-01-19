package com.iu.s7;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeController {
	
	@Inject
	private NoticeService noticeService;
	
	@RequestMapping(value="noticeList")
	public ModelAndView selectList(ListData listData) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar = noticeService.selectList(listData);
		mv.addObject("list", ar);
		mv.addObject("page", listData);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String insert(Model model) throws Exception{
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.POST)
	public ModelAndView insert(NoticeDTO noticeDTO) throws Exception{
		int result=noticeService.insert(noticeDTO);
		//1.result.jsp
		ModelAndView mv = new ModelAndView();
		String message="FAIL";
		if(result>0){
			message="SUCCESS";
		} 
		mv.addObject("message", message);
		mv.addObject("path", "noticeList");
		mv.setViewName("common/result");
		return mv;
	}
	
	
}

