package net.iyouyu.schedule;



import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

public class Main extends Activity {
	private MySQLiteOpenHelper myOpenHelper;
	private SQLiteDatabase mysql;
	private TextView tv1, tv2, tv3, tv4, tv5, tv6,tvview,tv1_2, tv2_2, tv3_2, tv4_2, tv5_2, tv6_2;
	private Button butleft,butright;
	int dayOfWeek,tmpday=4;
	private String name = "` ~", address = "` ~";
	
	
	//抽屉效果  
	private GridView gv;
	  private SlidingDrawer sd;
	  private ImageView im;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		System.out.println("进入onCreate");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);	

	}


	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			startActivityForResult(new Intent(this, Settings.class), 0);
			break;
		case R.id.item2:
			startActivityForResult(new Intent(this, SystemSet.class), 0);
			break;
		case R.id.item3:
			Intent intent = new Intent();
			intent.setClass(Main.this, About.class);
			Main.this.startActivity(intent);
			break;
		case R.id.item4:
			new AlertDialog.Builder(this)
					.setCancelable(false)
					.setIcon(android.R.drawable.btn_star)
					.setTitle("退出")
					.setMessage("您确认要退出程序吗？")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Main.this.finish(); // 关闭程序的核心方法
								}
							})
					.setNegativeButton("取消",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							}).show();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
	}
	
	public void insertView(int tmpdayOfWeek){
		 name = "` ~"; address = "` ~";
			butleft =(Button) findViewById(R.id.imageButton_left);
			butright =(Button) findViewById(R.id.imageButton_right);
			tv1 = (TextView) findViewById(R.id.textView1);tv1_2 = (TextView) findViewById(R.id.textView1_2);
			tv2 = (TextView) findViewById(R.id.textView2);tv2_2 = (TextView) findViewById(R.id.textView2_2);
			tv3 = (TextView) findViewById(R.id.textView3);tv3_2 = (TextView) findViewById(R.id.textView3_2);
			tv4 = (TextView) findViewById(R.id.textView4);tv4_2 = (TextView) findViewById(R.id.textView4_2);
			ImageView view= (ImageView) findViewById(R.id.imageView1);
		       view.setBackgroundResource(android.R.drawable.divider_horizontal_dim_dark);
		       view.setMinimumHeight(2);
		       view.setMinimumWidth(600);
		   ImageView view2= (ImageView) findViewById(R.id.imageView2);
		       view2.setBackgroundResource(android.R.drawable.divider_horizontal_dim_dark);
		       view2.setMinimumHeight(2);
		       view2.setMinimumWidth(600);
		 
			tv5 = (TextView) findViewById(R.id.textView5);tv5_2 = (TextView) findViewById(R.id.textView5_2);
			tv6 = (TextView) findViewById(R.id.textView6);tv6_2 = (TextView) findViewById(R.id.textView6_2);
		tvview =(TextView) findViewById(R.id.textviewday);
		

		myOpenHelper = new MySQLiteOpenHelper(this);
	    if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            mysql = SQLiteDatabase.openOrCreateDatabase(FileDB.f, null); 
        else
        	mysql = myOpenHelper.getReadableDatabase(); 
			tvview.setText(tmpdayOfWeek==0?"星期日":tmpdayOfWeek==1?"星期一":tmpdayOfWeek==2?"星期二":tmpdayOfWeek==3?"星期三":tmpdayOfWeek==4?"星期四":tmpdayOfWeek==5?"星期五":"星期六");
			
		Cursor cur = mysql.rawQuery("SELECT * FROM classes WHERE day = "
				+ tmpdayOfWeek + ";", null);
		if (cur != null) {

			while (cur.moveToNext()) {
				name += cur.getString(2) + "` ~";
				address += cur.getString(3) + "` ~";

			}

		}
		cur.close();
		if (mysql.isOpen())
			mysql.close();
		String[] namearray = name.split("` ~");
		String[] addressarray = address.split("` ~");

		tv1.setText(namearray[1]);tv1_2.setText(addressarray[1]);
		tv2.setText(namearray[2]);tv2_2.setText(addressarray[2]);
		tv3.setText(namearray[3]);tv3_2.setText(addressarray[3]);
		tv4.setText(namearray[4]);tv4_2.setText(addressarray[4]);
		tv5.setText(namearray[5]);tv5_2.setText( addressarray[5]);
		tv6.setText(namearray[6]);tv6_2.setText(addressarray[6]);

		butleft.setOnClickListener(new leftListener());
		butright.setOnClickListener(new rightListener());
		
	}
	
	
	class leftListener implements OnClickListener{
		
		public void onClick(View v){
		//	finish();
			Main.this.insertView((dayOfWeek+=6)%7);
	
			
		}
	}
	class rightListener implements OnClickListener{
		
		public void onClick(View v){
		//	finish();
			
			Main.this.insertView((++dayOfWeek)%7);
			
		}
	}
	
	
	protected void onResume() {
		// TODO Auto-generated method stub
		//实现返回即刷新

		super.onResume();
		LinearLayout locallinear =(LinearLayout)getLayoutInflater().inflate(R.layout.main, null);
		setContentView(this.deallayoiut(locallinear));


		
		butleft =(Button) findViewById(R.id.imageButton_left);
		butright =(Button) findViewById(R.id.imageButton_right);
		tv1 = (TextView) findViewById(R.id.textView1);tv1_2 = (TextView) findViewById(R.id.textView1_2);
		tv2 = (TextView) findViewById(R.id.textView2);tv2_2 = (TextView) findViewById(R.id.textView2_2);
		tv3 = (TextView) findViewById(R.id.textView3);tv3_2 = (TextView) findViewById(R.id.textView3_2);
		tv4 = (TextView) findViewById(R.id.textView4);tv4_2 = (TextView) findViewById(R.id.textView4_2);
		ImageView view= (ImageView) findViewById(R.id.imageView1);
	       view.setBackgroundResource(android.R.drawable.divider_horizontal_dim_dark);
	       view.setMinimumHeight(2);
	       view.setMinimumWidth(600);
	   ImageView view2= (ImageView) findViewById(R.id.imageView2);
	       view2.setBackgroundResource(android.R.drawable.divider_horizontal_dim_dark);
	       view2.setMinimumHeight(2);
	       view2.setMinimumWidth(600);
	 
		tv5 = (TextView) findViewById(R.id.textView5);tv5_2 = (TextView) findViewById(R.id.textView5_2);
		tv6 = (TextView) findViewById(R.id.textView6);tv6_2 = (TextView) findViewById(R.id.textView6_2);
		tvview =(TextView) findViewById(R.id.textviewday);


		//*判断是否存在SD卡，如果存在就创建一个数据库文件
			myOpenHelper = new MySQLiteOpenHelper(this);
		    if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		    	if(FileDB.f.exists())
	            mysql = SQLiteDatabase.openOrCreateDatabase(FileDB.f, null);               //无论数据库在哪里创建都需要首先声明这句
		    	else {
		    		FileDB.create();
		            if(FileDB.fileflag){
		    		 mysql = SQLiteDatabase.openOrCreateDatabase(FileDB.f, null);          //在sd卡创建数据库的语句1
		             myOpenHelper.onCreate(mysql);                                       //在sd卡创建数据库的语句2
		            }
		            else 
		            	mysql = myOpenHelper.getReadableDatabase(); 
		    	}
	        else
	        	mysql = myOpenHelper.getReadableDatabase(); 
		


		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		dayOfWeek = dayOfWeek < 0 || dayOfWeek > 6 ? 0 : dayOfWeek;
		tvview.setText(dayOfWeek==0?"星期日":dayOfWeek==1?"星期一":dayOfWeek==2?"星期二":dayOfWeek==3?"星期三":dayOfWeek==4?"星期四":dayOfWeek==5?"星期五":"星期六");
		Cursor cur = mysql.rawQuery("SELECT * FROM classes WHERE day = "
				+ dayOfWeek + ";", null);
		String name = "` ~", address = "` ~";
		if (cur != null) {
			while (cur.moveToNext()) {
				System.out.println(cur.getInt(0));
				System.out.println(cur.getInt(1));
				name += cur.getString(2) + "` ~";
				address += cur.getString(3) + "` ~";
			}
			
		}
		cur.close();
		if (mysql.isOpen())
			mysql.close();
		String[] namearray = name.split("` ~");
		String[] addressarray = address.split("` ~");

		tv1.setText(namearray[1]);tv1_2.setText(addressarray[1]);
		tv2.setText(namearray[2]);tv2_2.setText(addressarray[2]);
		tv3.setText(namearray[3]);tv3_2.setText(addressarray[3]);
		tv4.setText(namearray[4]);tv4_2.setText(addressarray[4]);
		tv5.setText(namearray[5]);tv5_2.setText( addressarray[5]);
		tv6.setText(namearray[6]);tv6_2.setText(addressarray[6]);
	
		butleft.setOnClickListener(new leftListener());
		butright.setOnClickListener(new rightListener());


		//加抽屉效果---------
			    /* 加载 Layout在 MyGridViewAdapter类中定义  */
			    /* 初始化对象 */
			    gv = (GridView)findViewById(R.id.myContent1); 
			    sd = (SlidingDrawer)findViewById(R.id.drawer1);
			    im=(ImageView)findViewById(R.id.myImage1);
			    gv.setBackgroundResource(R.drawable.openbg);	    
			    /* 使用定义的MyGridViewAdapter设置GridView里面的item内容 */
//			    MyGridViewAdapter adapter=new MyGridViewAdapter(this,items,icons);
//			    gv.setAdapter(adapter);
			    
			    /* 设定SlidingDrawer被打开的事件处理 */
			    sd.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener()
			    {
			      @Override
			      public void onDrawerOpened()
			      {
			        im.setImageResource(R.drawable.close);
			      }
			    });
			    /* 设置SlidingDrawer被关闭的事件处理 */
			    sd.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener()
			    {
			      @Override
			      public void onDrawerClosed()
			      {
			        im.setImageResource(R.drawable.open);
			      }
			    });
		
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
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	

}