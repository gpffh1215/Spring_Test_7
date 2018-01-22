package com.iu.s7;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
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
	
	@RequestMapping(value="noticeUpdate", method=RequestMethod.GET)
	public String update(int num, Model model) throws Exception{
		BoardDTO boardDTO= noticeService.selectOne(num);
		model.addAttribute("view", boardDTO);
		model.addAttribute("board", "notice");
		return "board/boardUpdate";

	}
	
	
	
	@RequestMapping(value="noticeDelete")
	public String delete(int num, HttpSession session) throws Exception{
		int result= noticeService.delete(num, session);
		
		return "redirect:./noticeList";
		
	}
	
	@RequestMapping(value="noticeView")
	public ModelAndView selectOne(int num)throws Exception{
		 BoardDTO boardDTO= noticeService.selectOne(num);
		 ModelAndView mv = new ModelAndView();
		 mv.addObject("view", boardDTO);
		 mv.addObject("board", "notice");
		 mv.setViewName("board/boardView");
		return mv;
	}
		
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
	public ModelAndView insert(NoticeDTO noticeDTO, MultipartFile [] file, HttpSession session) throws Exception{
		int result=noticeService.insert(noticeDTO, file, session);
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

