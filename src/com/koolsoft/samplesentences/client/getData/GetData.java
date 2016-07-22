package com.koolsoft.samplesentences.client.getData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.koolsoft.samplesentences.client.ClientUtils;
import com.koolsoft.samplesentences.client.resource.BHClientBundleBaseTheme;
import com.koolsoft.samplesentences.shared.Word;

public class GetData {
//	private Connection conn;
	private Map<String, Word> hashWord=new HashMap<String, Word>();
	private List<Word> listWord =new ArrayList<Word>();
	
//	public void getJsonData() {
//		try {
//			Class.forName("org.sqlite.JDBC");
//			conn = DriverManager.getConnection("jdbc:sqlite:language.db");
//			if (conn != null) {
//				System.out.println("Connected to the database");
//				getWord();
//				conn.close();
//			}
//		} catch (ClassNotFoundException ex) {
//			ex.printStackTrace();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//		}
//	}
//
//	
//	public void getWord(){
//		try {
//			String sql = "select * from language_16";
//			Statement stat = conn.createStatement();
//			ResultSet rs = stat.executeQuery(sql);
//			int i = 1;
//			listWord = new ArrayList<Word>();
//			while (rs.next()) {
//				Word word=new Word();
//				word.setWordId(rs.getString(1));
//				word.setParentId(rs.getString(3));
//				word.setMediaId(rs.getString(2));
//				word.setWordName(rs.getString(4));
//				word.setPhoneme(rs.getString(5));
//				listWord.add(word);
//				i++;
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void getData() {
		ClientUtils.log("doc: ");
		JSONValue object = JSONParser.parseStrict(BHClientBundleBaseTheme.IMPL.getBHMGWTClientBundle().getJson().getText());
		for(int i=0;i<object.isArray().size();i++){
			String wordName=object.isArray().get(i).isObject().get("wordName").isString().stringValue();
			String wordId=object.isArray().get(i).isObject().get("wordId").isString().stringValue();
			String mediaId=object.isArray().get(i).isObject().get("mediaId").isString().stringValue();
			String phoneme=object.isArray().get(i).isObject().get("phoneme").isString().stringValue();
			String parentId=object.isArray().get(i).isObject().get("wordName").isString().stringValue();
			
			Word word=new Word();
			word.setWordName(wordName);
			word.setMediaId(mediaId);
			word.setParentId(parentId);
			word.setPhoneme(phoneme);
			word.setWordId(wordId);
			listWord.add(word);
		}
	}
	
	
	public Map<String, Word> getHashWord() {
		return hashWord;
	}
	public List<Word> getListWord() {
		return listWord;
	}

	
	
}
