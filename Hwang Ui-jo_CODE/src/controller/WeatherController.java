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
		
		getTownForecast("36.789796","127.00184939999997");	// �ƻ�� �������� ������ �浵
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
				
				 data.put("hour",el.getChildTextTrim("hour") );     //���׿��� 3�ð� ���� 
	             data.put("day",el.getChildTextTrim("day") );     //1��°�� (0: ����/1: ����/2: ��)
	             data.put("temp",el.getChildTextTrim("temp") );    //���� �ð��µ�
	             data.put("tmx",el.getChildTextTrim("tmx") );    //�ְ� �µ� 
	             data.put("tmn",el.getChildTextTrim("tmn") );    //���� �µ�
	             data.put("sky",el.getChildTextTrim("sky") );    //�ϴ� �����ڵ� (1: ����, 2: ��������, 3:��������, 4:�帲)
	             data.put("pty",el.getChildTextTrim("pty") );    //���� �����ڵ� (0: ����, 1: ��, 2: ��/��, 3: ��/��, 4: ��)
	             data.put("wfkor",el.getChildTextTrim("wfKor") ); //���� �ѱ���
	             data.put("pop",el.getChildTextTrim("pop") );     //���� Ȯ��%
	             data.put("r12",el.getChildTextTrim("r12") );     //12�ð� ���� ������
	             data.put("s12",el.getChildTextTrim("s12") );     //12�ð� ���� ������
	             
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
			inKangsu = ("������ ���� �����Դϴ�.");
			inExKangsu = ("���� ����Ȯ�� : " + data.get("pop") + "%");
			
			break;
		case "1":	
			inKangsu = ("�� �� �� �ֽ��ϴ�.");
			inExKangsu = ("���� ����Ȯ�� : " + data.get("pop") + "%");
			break;
		case "2||3":
			inKangsu = ("�� �� ���� ���� �� �ֽ��ϴ�.");
			inExKangsu = ("���� ����Ȯ�� : " + data.get("pop") + "%");
			break;
		case "4":
			inKangsu = ("���� ���� �� �ֽ��ϴ�.");
			inExKangsu = ("���� ����Ȯ�� : " + data.get("pop") + "%");
			break;
	}
		
		
	}
}	






