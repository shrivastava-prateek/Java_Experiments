package com.psl.main;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub\\
		JSONObject jsonObj = new JSONObject();
		JSONParser parser = new JSONParser();
		Object obj = parser.parse("{\"A\":\"B\"}");
		jsonObj = (JSONObject)obj;
		System.out.println(jsonObj);
		checkingList();

	}
	
	
	private static void checkingList(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		
		System.out.println(list.size()>>1);
	}

}
