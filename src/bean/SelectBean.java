package bean;

import java.io.Serializable;

public class SelectBean implements Serializable {

	private String text;
	private String tag;
	private String time;
	private int th_id;
	private int user_id;
	private String user_name;

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
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getTh_id() {
		return th_id;
	}
	public void setTh_id(int th_id) {
		this.th_id = th_id;
	}
	public int getUserid() {
		return user_id;
	}
	public void setUserid(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return user_name;
	}
	public void setUserName(String user_name) {
		this.user_name = user_name;
	}

}