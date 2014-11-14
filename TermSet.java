package net.iyouyu.schedule;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;
import android.widget.Toast;

public class TermSet extends Activity{
	private DatePicker dp;
	private TextView tv;
	private int my_year,my_month,my_day ;
	private String tempdate;
	private Button butok;
	private MySQLiteOpenHelper myOpenHelper;
	private   SQLiteDatabase  mysql ; // 实例数据库
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		super.onCreate(savedInstanceState);
		LinearLayout locallinear =(LinearLayout)getLayoutInflater().inflate(R.layout.term, null);
		setContentView(this.deallayoiut(locallinear));


		  myOpenHelper = new MySQLiteOpenHelper(this);
	        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
	            mysql = SQLiteDatabase.openOrCreateDatabase(FileDB.f, null); 
	        else
	        	mysql = myOpenHelper.getWritableDatabase(); 
		butok=(Button)findViewById(R.id.buttonok);
		dp=(DatePicker) this.findViewById(R.id.datePicker1);
		tv=(TextView)findViewById(R.id.textView_runtime);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		my_year = calendar.get(Calendar.YEAR);
		my_month = calendar.get(Calendar.MONTH);
		my_day = calendar.get(Calendar.DAY_OF_MONTH);
		 Cursor cur = mysql.rawQuery("SELECT * FROM term WHERE id="+1+";",null);
	        if (cur != null) {
		          while (cur.moveToNext()) {//直到返回false说明表中到了数据末尾
		        	  tempdate = cur.getString(1); // 参数0 指的是列的下标,这里的0指的是id列
		          }
		  		if(tempdate!=null){
		          String str[] =tempdate.split("-");
		          my_year=Integer.parseInt(str[0]);
		          my_month=Integer.parseInt(str[1])-1;
		          my_day=Integer.parseInt(str[2]);
		  		}
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
					try {
						java.util.Date date;
						if(tempdate!=null){
							 date=sdf.parse(tempdate);
					  		}
						else{
							my_month++;
							 date=sdf.parse(my_year+"-"+my_month+"-"+my_day);
							 my_month--;
						}
					
						Calendar c = Calendar.getInstance();
						c.setTime(new Date(System.currentTimeMillis()));
					    long day =	(c.getTimeInMillis()-date.getTime())/86400000+1;
						tv.setText("已经开学"+day+"天");
						System.out.println(day+"asdasdasd");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
	    	cur.close();
			System.out.println("开始查询3");
		 dp.init(my_year, my_month, my_day,new OnDateChangedListener(){
		 @Override
		 public void onDateChanged(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			 my_year = year;
			 my_month = monthOfYear+1;
			 my_day = dayOfMonth;
			}
			});
	 
		butok.setOnClickListener(new okListener());
		
	}
	class AbourexitListener implements OnClickListener{
		
		public void onClick(View v){
			finish();
			
		}
		
	}
	class okListener implements OnClickListener{
		
		public void onClick(View v){
		
		//	Main.this.insertView((dayOfWeek+=6)%7);
		
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
//				String dstr=my_year+"-"+my_month+1+"-"+my_day; 
//				java.util.Date date=sdf.parse(dstr);

			  mysql.execSQL("UPDATE term SET startterm='"+my_year+"-"+my_month+"-"+my_day+"' WHERE id="+1+";");
		//	System.out.println(my_year+"--"+my_month+"--"+my_day);
			 Toast.makeText(TermSet.this, "开学时间修改成功", Toast.LENGTH_LONG).show();
			finish();
	
			
		}
	}
	

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
    	if (keyCode == KeyEvent.KEYCODE_BACK ) {
                // do something on back.
	 		if(mysql.isOpen())
	 			mysql.close();
               return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(mysql.isOpen())
 			mysql.close();
		super.onPause();
	}
	//处理 壁纸的选择对layout的设置
	private LinearLayout deallayoiut(LinearLayout locallinear){
		   SharedPreferences prs = getSharedPreferences("schedule.xm",Context.MODE_PRIVATE);  
	       int num = prs.getInt("bgnum", 0);
		if(num==0||num==1)
			locallinear.setBackgroundResource(R.drawable.bg_hdpi);
			else if(num==2)
				locallinear.setBackgroundResource(R.drawable.bg2);
			else if(num==3)
				locallinear.setBackgroundResource(R.drawable.bg3);
			else if(num==4)
				locallinear.setBackgroundResource(R.drawable.bg4);
		return locallinear;
		
	}
}
