package com.iu.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class FileDown extends AbstractView {
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		File file= (File)model.get("down");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength((int)file.length());
		
		String fileName = URLEncoder.encode((String)model.get("oname"), "UTF-8"); // frequently used
		
		response.setHeader("Content-Disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Contents-Transfer-Encoding", "binary");
		
		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fi, os);
		
		fi.close();
		os.close();
		
	}
	
	
	
	
}
