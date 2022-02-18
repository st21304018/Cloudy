package bean;

import java.io.Serializable;

public class SelectBean implements Serializable {

	private String text;
	private String tag;
	private String time;
	private String name;
	private String user_id;
	private String check;
	private int likes;
	private int th_id;
	private int reply_id;


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

	public String getUserid() {
		return user_id;
	}
	public void setUserid(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTh_id() {
		return th_id;
	}
	public void setTh_id(int th_id) {
		this.th_id = th_id;
	}
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public String getCheck() {
		return check;
	}
	public void setCheck(String check) {
		this.check = check;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}

}