package com.vtalent.ReadEmailBody;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadRegEmailBody {
	
	public static String readEmailBody(String fullName,String pwd,String fileName) throws IOException {
		
		String url="";
		String mailBody=null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			StringBuffer stringBuffer = new StringBuffer();
			
			String line = bufferedReader.readLine();
			
			while (line != null) {
				
				stringBuffer.append(line);
				line=bufferedReader.readLine();	
			}
			
			bufferedReader.close();
			
			mailBody=stringBuffer.toString();
			mailBody=mailBody.replace("{Name}", fullName);
			mailBody=mailBody.replace("{Password}", pwd);
			mailBody=mailBody.replace("{PWD}", pwd);
			mailBody=mailBody.replace("{URL}", url);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mailBody;
	}

}
