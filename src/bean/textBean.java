package bean;
	import java.io.Serializable;

	public class textBean implements Serializable {

		private String text;
		private String tag;

		public String getText() {
			return text;
		}
		public void setText(String text){
			this.text = text;
		}
		public String getTag() {
			return tag;
		}
		public void setTag(String tag){
			this.tag = tag;
		}

	}



