package com.ptit.mining;
import java.io.PrintWriter;

public class Cmd {
	public static void runCmd(String content) {
		String[] command = { "cmd", };
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);

			new Thread(new SVMlight(p.getErrorStream(), System.err)).start();
			new Thread(new SVMlight(p.getInputStream(), System.out)).start();
			PrintWriter stdin = new PrintWriter(p.getOutputStream());
			stdin.println(content);

			stdin.close();
			p.waitFor();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
