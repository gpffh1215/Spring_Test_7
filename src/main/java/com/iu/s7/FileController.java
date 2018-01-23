package com.iu.s7;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.notice.NoticeDAO;
import com.iu.notice.NoticeService;

@Controller
@RequestMapping(value="/file/**")
public class FileController {
	
	@Inject
	private FileDAO fileDAO;
	
	
	@RequestMapping(value="fileDelete")
	public String fileDelete(FileDTO fileDTO, HttpSession session, Model model)throws Exception{
		String filepath = session.getServletContext().getRealPath("resources/upload");
		int result= fileDAO.deleteFnum(fileDTO.getFnum());
		if(result>0){
			File file = new File(filepath, fileDTO.getFname());
			if(file.delete()){
				result=1;
			} else {
				result=0;
			}
		}
		
		model.addAttribute("result", result);
		return "common/fileResult";
	}
	
	
	@RequestMapping(value="fileDown")
	public ModelAndView fileDown(FileDTO fileDTO, HttpSession session) throws Exception{
		String filePath= session.getServletContext().getRealPath("resources/upload");
		File file = new File(filePath, fileDTO.getFname());
		ModelAndView mv = new ModelAndView();
		mv.addObject("down", file);
		mv.addObject("oname", fileDTO.getOname());
		mv.setViewName("fileDown");
		
		return mv;
	}
	
	
}