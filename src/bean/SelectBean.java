package bean;

import java.io.Serializable;

public class SelectBean implements Serializable {

	private String text;
	private String tag;

	public void setText(String text){

		this.text = text;

	}
	public void setTag(String tag){
		this.tag = tag;
	}
	public String getText(){
		return text;
	}
	public String getTag(){
		return tag;
	}

}