package com.psl.main;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub\\
		checkingList();

	}
	
	
	private static void checkingList(){
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(1);
		
		System.out.println(list.size()>>1);
	}

}
