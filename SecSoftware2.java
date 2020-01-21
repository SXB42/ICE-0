
public class SecSoftware2 {

	public static void main(String[] args) {
      
		String s = "hello :)";
		
		//violation 3
		//Do not catch nullpointerexception or its ancestors. Just add null checks instead
		//as doing this can create more performance overhead. it's also difficult to determine which 
		//block threw an exception when there's multiple try blocks. 
		//https://wiki.sei.cmu.edu/confluence/display/java/ERR08-J.+Do+not+catch+NullPointerException+or+any+of+its+ancestors
		isName(s);
	}
	
	public static boolean isName(String s) {
		try {
			String names[] = s.split(" ");
			
			if(names.length != 2) {
				return false;
			}
			
			return (isCapitalized(names[0]) && isCapitalized(names[1]));
		}
		catch (NullPointerException e) {
			return false;
		}
	}
	
	//code just so that isCapitalized exists, doesn't appear in documentation so just copy
	//this when fixing it
	public static boolean isCapitalized(String ch) {
		return true;
	}

}
