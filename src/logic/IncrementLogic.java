package logic;

import bean.Board;
import sql.InsertSQL;
import sql.SelectSQL;

public class IncrementLogic {
	public void incrementLogic(Board bean) {
		String incrementSql = "select th_likes from cloudy_thread where th_id="+bean.getId()+"";
		SelectSQL select = new SelectSQL();
		int i = select.selectSQL(incrementSql);
		i++;

		String updateSql = "update cloudy_thread set th_likes = "+ i +" where th_id= "+bean.getId()+"";
		InsertSQL up = new InsertSQL();
		up.insertSQL(updateSql);
	}
}
