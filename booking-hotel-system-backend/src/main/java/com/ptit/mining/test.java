package com.ptit.mining;

import java.io.IOException;

import vn.hus.nlp.tokenizer.VietTokenizer;

public class test {

	public static void main(String[] args) throws IOException {
		VietTokenizer token;
		token = new VietTokenizer();
		String s = "Khách sạn này quá đẹp";
		boolean i = VnToken.isNagative(s);
		System.out.println(i);

	}

}
