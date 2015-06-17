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

public class UploadImageServlet extends HttpServlet {

	private String uploadPath = "shop/upload/"; // 上传文件的目录
	private String tempPath = "shop/uploadtmp/"; // 临时文件目录
	private String serverPath = null;
	
	private int sizeMax = 3;//图片最大上限
	private String[] fileType = new String[]{".jpg",".gif",".bmp",".png",".jpeg",".ico"};

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		serverPath = getServletContext().getRealPath("/").replace("\\", "/");
		//serverPath = request.getContextPath();
		//Servlet初始化时执行,如果上传文件目录不存在则自动创建
		if(!new File(serverPath+uploadPath).isDirectory()){
			new File(serverPath+uploadPath).mkdirs();
		}
		if(!new File(serverPath+tempPath).isDirectory()){
			new File(serverPath+tempPath).mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(5*1024); //最大缓存
		factory.setRepository(new File(serverPath+tempPath));//临时文件目录
		
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(sizeMax*1024*1024);//文件最大上限
		
		String filePath = null;
		try {
			List<FileItem> items = upload.parseRequest(request);//获取所有文件列表
			for (FileItem item : items) {
				//获得文件名，这个文件名包括路径
				if(!item.isFormField()){
					//文件名
					String fileName = item.getName().toLowerCase();
					
					if(fileName.endsWith(fileType[0])||fileName.endsWith(fileType[1])||fileName.endsWith(fileType[2])||fileName.endsWith(fileType[3])||fileName.endsWith(fileType[4])||fileName.endsWith(fileType[5])){
						String uuid = UUID.randomUUID().toString();
						String lrex = uploadPath+uuid+fileName.substring(fileName.lastIndexOf("."));
						filePath = serverPath+lrex;
						item.write(new File(filePath));
						request.setAttribute("path", lrex);
						request.getRequestDispatcher("/user/register_commodity.jsp").forward(request,
								response);
					//	response.sendRedirect(request.getHeader("Referer"));
					}else{
						request.setAttribute("errorMsg", "上传失败,请确认上传的文件存在并且类型是图片!");
						request.getRequestDispatcher("/user/register_commodity.jsp").forward(request,
								response);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "上传失败,请确认上传的文件大小不能超过"+sizeMax+"M");
			request.getRequestDispatcher("/user/register_commodity.jsp").forward(request,
					response);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
	
}