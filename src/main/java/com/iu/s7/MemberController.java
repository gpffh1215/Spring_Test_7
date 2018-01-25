package com.iu.s7;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.member.MemberDTO;
import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {

	@Inject
	private MemberService memberService;
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.GET)
	public void memberUpdate(){}
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.GET)
	public ModelAndView memberUpdate(MemberDTO memberDTO, MultipartFile file, HttpSession session)throws Exception{
		int result= memberService.memberUpdate(memberDTO, file, session);
		String message="Update Fail";
		if(result>0){
			session.setAttribute("member", memberDTO);
			message="Update Success";
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", message);
		mv.addObject("path", "./memberView");
		mv.setViewName("common/result");
		
		return mv;
	}
	
	
	
	
	@RequestMapping(value="memberJoin", method=RequestMethod.GET)
	public String memberJoin(Model model) throws Exception{
		MemberDTO memberDTO= new MemberDTO();
		model.addAttribute("memberDTO", memberDTO);
		return "home";
	}
	
	@RequestMapping(value="memberJoin", method=RequestMethod.POST)
	public ModelAndView memberJoin2(MemberDTO memberDTO) throws Exception{
		int result = memberService.memberJoin(memberDTO);
		ModelAndView mv = new ModelAndView();
		String message="FAIL";
		if(result>0){
			message="SUCCESS";
		}
		mv.addObject(message, "message");
		mv.addObject("path", "home");
		mv.setViewName("common/result");
		
		return mv;
		
	}
	
	
	
}
