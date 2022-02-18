package logic;

import bean.Board;
import bean.SelectBean;
import sql.InsertSQL;
import sql.SelectSQL;

public class DecrementLogic {
	public void decrementLogic(Board bean) {
		String incrementSql = "select th_likes from cloudy_thread where th_id="+bean.getId()+"";
		SelectSQL select = new SelectSQL();
		int i = select.selectSQL(incrementSql) - 1;

		String updateSql = "update cloudy_thread set th_likes = "+ i +" where th_id= "+bean.getId()+"";
		InsertSQL up = new InsertSQL();
		up.insertSQL(updateSql);
	}
	public void decrementRepLogic(SelectBean bean) {
		String incrementSql = "select reply_likes from cloudy_reply where reply_id="+bean.getReply_id()+"";
		SelectSQL select = new SelectSQL();
		int i = select.selectSQL(incrementSql) - 1;

		String updateSql = "update cloudy_reply set reply_likes = "+ i +" where reply_id= "+bean.getReply_id()+"";
		InsertSQL up = new InsertSQL();
		up.insertSQL(updateSql);
	}
}

