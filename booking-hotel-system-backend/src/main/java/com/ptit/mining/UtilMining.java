package com.ptit.mining;

import java.io.IOException;

public class UtilMining {
	public static void mining(String s) throws IOException {
		String st = s.toLowerCase();

		Cmd.runCmd("svm_classify data/test.dat data/model data/predictions");
		boolean isNega = VnToken.isNagative(st);
	}

}
