package net.iyouyu.schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Link extends Activity {
	private MySQLiteOpenHelper myOpenHelper;
	private SQLiteDatabase mysql;
	private String name = "` ~", address = "";
	private int my_year,my_month,my_day ;
	private String tempdate;

	public void doWork() {
		FileDB.create();
		myOpenHelper = new MySQLiteOpenHelper(this);
		//System.out.println("+++"+ android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED));
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			
			//System.sleep(200);
			System.out.println("开始实例化数据库1");
		//	myOpenHelper = new MySQLiteOpenHelper(this);// 实例一个数据库辅助器
			mysql = SQLiteDatabase.openOrCreateDatabase(FileDB.f, null); // 实例数据库
			if (FileDB.fileflag) {
				myOpenHelper.onCreate(mysql);
				// myOpenHelper.onInsert(mysql,1,1,"Java编程","东区3教903");
				System.out.println("实例化数据库完成1");
				
			}
	
			// ------在SD卡创建数据库
		} else {
			System.out.println("开始实例化数据库2-----1");

			//myOpenHelper = new MySQLiteOpenHelper(this);
			System.out.println("开始实例化数据库2----2");
			mysql = myOpenHelper.getReadableDatabase();

			System.out.println("实例化数据库完成2");
	
		}

	}

	public String doSelect() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		dayOfWeek = dayOfWeek < 0 || dayOfWeek > 6 ? 0 : dayOfWeek;

		Cursor cur = mysql.rawQuery("SELECT * FROM classes WHERE day = "
				+ dayOfWeek + ";", null);
		// System.out.println("111");
		if (cur != null) {
			// String temp = "";
			// String temp1="";
			// int i = 0;
			while (cur.moveToNext()) {
				System.out.println(cur.getInt(0));
				System.out.println(cur.getInt(1));
				name += cur.getString(2) + "` ~";
				// address +=cur.getString(3)+"` ~";
			}

		}
		cur.close();
		return name;

	}
	public long doSelectterm() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		System.out.println(c.get(Calendar.DAY_OF_WEEK));
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		dayOfWeek = dayOfWeek < 0 || dayOfWeek > 6 ? 0 : dayOfWeek;

		Cursor cur = mysql.rawQuery("SELECT * FROM term  id="+1+";", null);
		if (cur != null) {
	          while (cur.moveToNext()) {//直到返回false说明表中到了数据末尾
	        	  tempdate = cur.getString(1); // 参数0 指的是列的下标,这里的0指的是id列
	          }
	          String str[] =tempdate.split("-");
	          my_year=Integer.parseInt(str[0]);
	          my_month=Integer.parseInt(str[1])-1;
	          my_day=Integer.parseInt(str[2]);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
			//	String dstr=my_year+"-"+my_month+1+"-"+my_day; 
				try {
					java.util.Date date=sdf.parse(tempdate);
					Calendar cc = Calendar.getInstance();
					cc.setTime(new Date(System.currentTimeMillis()));
				    long day =	(cc.getTimeInMillis()-date.getTime())/86400000+1;
					//tv.setText("已经开学"+day+"天");
				   System.out.println("asdasd"+day);
				    return day;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		cur.close();
		return 0;

	}


	public void doClose() {

		if (mysql.isOpen())
			mysql.close();
	}
}
