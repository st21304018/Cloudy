package logic;

public class NewLineLogic {

	public static String htmlEscape(String str){
		StringBuffer result = new StringBuffer();
		for(char c : str.toCharArray()) {
			switch (c) {
			case '&' :
				result.append("&amp;");
				break;
			case '<' :
				result.append("&lt;");
				break;
			case '>' :
				result.append("&gt;");
				break;
			case '"' :
				result.append("&quot;");
				break;
			case '\'' :
				result.append("&#39;");
				break;
			case ' ' :
				result.append("&nbsp;");
				break;
			default :
				result.append(c);
				break;
			}
		}
		return result.toString();
	}
}