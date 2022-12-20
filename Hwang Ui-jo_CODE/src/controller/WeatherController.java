package controller;

import java.awt.Color;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class WeatherController {
	
	private LayoutController layoutCon;
	private HomeController homeCon;
	private String rssFeed = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx=%s&gridy=%s";
	static List<Map<String, String>> result = new ArrayList<Map<String, String>>();
	static Map<String, String> data = new LinkedHashMap<String, String>();
	private Image weather_img;
	@FXML private ImageView imgView;
	@FXML private Label Kangsu;
	@FXML private Label exKangsu;
	private String inKangsu;
	private String inExKangsu;
	
	public void setWeather() {
		
		getTownForecast("36.789796","127.00184939999997");	// 아산시 탕정면의 위도와 경도
		inData();
		
		imgView.setImage(weather_img);
		Kangsu.setText(inKangsu);
		exKangsu.setText(inExKangsu);
		
	}
	
	public List<Map<String, String>> getTownForecast(String x, String y) {
		try {
			SAXBuilder parser = new SAXBuilder();
			parser.setIgnoringElementContentWhitespace(true);
			
			String url = String.format(rssFeed, x,y);
			Document doc = parser.build(url);
			Element root = doc.getRootElement();
			
			Element channel = root.getChild("body");
			List<Element> list = channel.getChildren("data");
			
			for(int i=0; i<1; ++i) {
				Element el = (Element)list.get(i);
				
				 data.put("hour",el.getChildTextTrim("hour") );     //동네예보 3시간 단위 
	             data.put("day",el.getChildTextTrim("day") );     //1번째날 (0: 오늘/1: 내일/2: 모레)
	             data.put("temp",el.getChildTextTrim("temp") );    //현재 시간온도
	             data.put("tmx",el.getChildTextTrim("tmx") );    //최고 온도 
	             data.put("tmn",el.getChildTextTrim("tmn") );    //최저 온도
	             data.put("sky",el.getChildTextTrim("sky") );    //하늘 상태코드 (1: 맑음, 2: 구름조금, 3:구름많음, 4:흐림)
	             data.put("pty",el.getChildTextTrim("pty") );    //강수 상태코드 (0: 없음, 1: 비, 2: 비/눈, 3: 눈/비, 4: 눈)
	             data.put("wfkor",el.getChildTextTrim("wfKor") ); //날씨 한국어
	             data.put("pop",el.getChildTextTrim("pop") );     //강수 확률%
	             data.put("r12",el.getChildTextTrim("r12") );     //12시간 예상 강수량
	             data.put("s12",el.getChildTextTrim("s12") );     //12시간 예상 적설량
	             
	          //   System.out.println(data);
	             result.add(data);
			}
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public void inData() {
	
		try {
			switch(data.get("sky")) {
			case "1":
				weather_img = new Image("file:Image/sun.png");
			
				break;
			case "2":
				weather_img = new Image("file:Image/sunnycloudy.png");
				break;
			case "3":
				weather_img = new Image("file:Image/manycloudy.png");
				
				break;
			case "4":
				weather_img = new Image("file:Image/cloud.png");
				break;
			}


		}catch(Exception e) {
			e.printStackTrace();
		}
		
		switch(data.get("pty")) {
		case "0":
			inKangsu = ("날씨가 맑을 예정입니다.");
			inExKangsu = ("예상 강수확률 : " + data.get("pop") + "%");
			
			break;
		case "1":	
			inKangsu = ("비가 올 수 있습니다.");
			inExKangsu = ("예상 강수확률 : " + data.get("pop") + "%");
			break;
		case "2||3":
			inKangsu = ("비 나 눈이 내릴 수 있습니다.");
			inExKangsu = ("예상 강수확률 : " + data.get("pop") + "%");
			break;
		case "4":
			inKangsu = ("눈이 내릴 수 있습니다.");
			inExKangsu = ("예상 강수확률 : " + data.get("pop") + "%");
			break;
	}
		
		
	}
}	






