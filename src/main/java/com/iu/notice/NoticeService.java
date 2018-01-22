package com.iu.notice;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;
@Service
public class NoticeService implements BoardService {
	@Inject
	private NoticeDAO noticeDAO;
	@Inject
	private FileDAO fileDAO;
	
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = noticeDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return noticeDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return noticeDAO.selectOne(num);

	}

	@Override
	public int insert(BoardDTO boardDTO, MultipartFile [] file, HttpSession session) throws Exception {
		FileSaver fileSaver = new FileSaver();
		String filepath = session.getServletContext().getRealPath("resources/upload");
		System.out.println(filepath);
		File f = new File(filepath);
		if(!f.exists()){
			f.mkdirs();
		}

		List<String> names= fileSaver.saver(file, filepath);

		for(int i = 0; i < names.size(); i++){
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(file[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum());
			fileDAO.insert(fileDTO);
		}
		return 1;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}

	@Override
	public int delete(int num, HttpSession session) throws Exception {
		String filepath = session.getServletContext().getRealPath("resources/upload");
		List<FileDTO> ar = fileDAO.selectList(num);
		int result= noticeDAO.delete(num);
		result= fileDAO.delete(num);
		
		for(FileDTO fileDTO: ar){
		try{
			File file = new File(filepath, fileDTO.getFname());
			file.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
		}	
		
		return result;
	}

}
