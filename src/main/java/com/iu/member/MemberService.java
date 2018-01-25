package com.iu.member;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.util.FileSaver;


@Service
public class MemberService {
	
	@Inject
	private MemberDAO memberDAO;
	
	
	public int memberJoin(MemberDTO memberDTO) throws Exception{
		return memberDAO.memberJoin(memberDTO);
	}
	
	public int memberUpdate(MemberDTO memberDTO, MultipartFile file, HttpSession session) throws Exception{
		if(file != null){
			String filePath= session.getServletContext().getRealPath("resources/upload");
			File f = new File(filePath);
			if(!f.exists()){
				f.mkdirs();
			}
			FileSaver fs = new FileSaver();
			String fileName= fs.saver(file, filePath);
			memberDTO.setFname(fileName);
			memberDTO.setOname(file.getOriginalFilename());
		} else {
			memberDTO.setFname(((MemberDTO)session.getAttribute("member")).getFname());
			memberDTO.setOname(((MemberDTO)session.getAttribute("member")).getOname());
		}
		
		
		return memberDAO.memberUpdate(memberDTO, file, session);
	}
	
	
}
