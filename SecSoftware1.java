import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecSoftware1 {

	public static void main(String[] args) {
	    
	String string = "hello";
	//violation 1
	//the standard that has been violated is assuming that
	//a Java char fully represents a Unicode code point. Ignores supplementary characters introduced in later versions of Java.
	//https://wiki.sei.cmu.edu/confluence/display/java/STR01-J.+Do+not+assume+that+a+Java+char+fully+represents+a+Unicode+code+point
    trim(string);
	
    //violation 2
    //the standard that has been violated here is performing a string modification before validation
    //This may allow an attacker to bypass validation and is susceptible to XSS
    //https://wiki.sei.cmu.edu/confluence/display/java/IDS11-J.+Perform+any+string+modifications+before+validation
    String maliciousInput = "<scr" + "\uFDEF" + "ipt>";
    String sb = filterString(maliciousInput);
    //sb = "<script>"
	}

	public static String trim(String string) {
		
		char ch;
		int i;
		for (i = 0; i < string.length(); i += 1) {
			ch = string.charAt(i);
			if(!Character.isLetter(ch)) {
				break;
			}
		}
		
		return string.substring(i);
	}
	
		
	public static String filterString(String str) {
		String s = Normalizer.normalize(str,  Form.NFKC);
		
		//validate input
		Pattern pattern = Pattern.compile("<script>");
		Matcher matcher = pattern.matcher(s);
		
		if(matcher.find())
			throw new IllegalArgumentException("Invalid Input");
		
		s = s.replaceAll("[\\p{Cn}]", "");
		return s;
	}
}
