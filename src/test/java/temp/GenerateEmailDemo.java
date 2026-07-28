package temp;

import java.util.Date;


public class GenerateEmailDemo {
	public static void main(String[] args) {
		Date date= new Date();
		  String dateString= date.toString();
		 String noSpaceString= dateString.replaceAll(" ", "");
		 System.out.println(noSpaceString);
		 String noSpaceAndColonString=noSpaceString.replaceAll(":", "");
		 System.out.println(noSpaceAndColonString);
		 String emailWithTimeStamp=noSpaceAndColonString+"gmail.com";
		 System.out.println(emailWithTimeStamp);
		 
	}

	

}
