package com.tao.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		/*
		 * 上传三步
		 */
		// 工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 解析器
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 解析，得到List
		try {
			List<FileItem> list = sfu.parseRequest(request);
			FileItem fi = list.get(1);
			
			
			/*
			 * 1. 得到文件保存的路径
			 */
			String root = this.getServletContext().getRealPath("/WEB-INF/files/");
System.out.println(root);
			/*
			 * 2. 生成二层目录
			 *   1). 得到文件名称
			 *   2). 得到hashCode
			 *   3). 转发成16进制
			 *   4). 获取前二个字符用来生成目录
			 */
			String filename = fi.getName();//获取上传的文件名称
			/*
			 * 处理文件名的绝对路径问题
			 */
			int index = filename.lastIndexOf("\\");
			if(index != -1) {
				filename = filename.substring(index+1);
			}
			/*
			 * 给文件名称添加uuid前缀，处理文件同名问题
			 */
			String savename = UploadServlet.uuid() + "_" + filename;
			
			/*
			 * 1. 得到hashCode
			 */
			int hCode = filename.hashCode();
			String hex = Integer.toHexString(hCode);
			
			/*
			 * 2. 获取hex的前两个字母，与root连接在一起，生成一个完整的路径
			 */
			File dirFile = new File(root, hex.charAt(0) + "/" + hex.charAt(1));
			
			/*
			 * 3. 创建目录链
			 */
			dirFile.mkdirs();
			
			/*
			 * 4. 创建目录文件
			 */
			File destFile = new File(dirFile, savename);
			
			/*
			 * 5. 保存
			 */
			fi.write(destFile);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 返回一个不重复的字符串
	 * @return
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
}