package com.koolsoft.samplesentences.server;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.koolsoft.samplesentences.shared.Word;

@WebServlet(name = "FileUploadServlet", urlPatterns = { "/utils" })
@MultipartConfig
public class ServerUtils extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static final Logger LOGGER = Logger.getLogger(ServerUtils.class.getCanonicalName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");
		final PrintWriter writer = response.getWriter();
		String result = "";
		try {
			FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(request);
			while (fileItemIterator.hasNext()) {
				FileItemStream fileItemStream = fileItemIterator.next();
				if (fileItemStream.isFormField()) {
					System.out.println("Got a form field: " + fileItemStream.getFieldName() + " "
							+ Streams.asString(fileItemStream.openStream()));
				} else {
					InputStream is = fileItemStream.openStream();
					String myString = IOUtils.toString(is, "UTF-8");
					Type listType = new TypeToken<ArrayList<Word>>() {
					}.getType();
					List<Word> listData = new Gson().fromJson(myString, listType);
					LOGGER.info("aa: " + listData.size());
					GreetingServiceImpl impl = new GreetingServiceImpl();
					impl.upListWord(listData);
					response.getWriter().append("<br>Upload file thành công!!!");
					
				}
			}
		} catch (FileUploadException e) {
		} finally {
		}
		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");
		String type = request.getParameter("type");
		LOGGER.info("text: " + type);
		if(type.equals("upload")){
			request.getRequestDispatcher("/WEB-INF/UploadFile.jsp").forward(request, response);
		}
		else if(type.equals("addFTS")){
			GreetingServiceImpl impl=new GreetingServiceImpl();
			impl.addFTSListWord();
			response.getWriter().append("<br>add oke!!!");
		}
		else if(type.equals("newsample")){
			GreetingServiceImpl impl=new GreetingServiceImpl();
			impl.newListSample();
		}
	
	}
}
