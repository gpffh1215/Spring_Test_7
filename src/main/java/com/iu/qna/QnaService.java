package com.iu.qna;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	@Inject
	private FileDAO fileDAO;
	
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = qnaDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return qnaDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<FileDTO> ar = fileDAO.selectList(num);
		mv.addObject("fileList", ar);
		mv.addObject("view", qnaDAO.selectOne(num));
		mv.addObject("board", "qna");
		mv.setViewName("board/boardView");
		return qnaDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO, MultipartFile [] file, HttpSession session) throws Exception {
		return qnaDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(int num, HttpSession session) throws Exception {
		String filepath = session.getServletContext().getRealPath("resources/upload");
		List<FileDTO> ar = fileDAO.selectList(num);
		int result= qnaDAO.delete(num);
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
	
	public int replyInsert(BoardDTO boardDTO) throws Exception{
		 qnaDAO.replyUpdate(boardDTO);
		 return qnaDAO.replyInsert(boardDTO);
	}
	
	
	

}
