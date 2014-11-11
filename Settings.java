package net.iyouyu.schedule;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

//�̳�PreferenceActivity����ʵ��OnPreferenceChangeListener��OnPreferenceClickListener�����ӿ�
public class Settings extends PreferenceActivity implements OnPreferenceClickListener,
OnPreferenceChangeListener{
	//������ر���
	MySQLiteOpenHelper myOpenHelper;
	  SQLiteDatabase  mysql ; // ʵ�����ݿ�
	String updateFrequencyKey;
	String strclassno;
	String strclassdetail;
	String strclassname;
	String strclassaddress;
	int day=1,no=1;
	ListPreference updateFrequencyListPref;
	ListPreference classno;
	PreferenceCategory classdetail;
	EditTextPreference classname;
	EditTextPreference classaddress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myOpenHelper = new MySQLiteOpenHelper(this);
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
            mysql = SQLiteDatabase.openOrCreateDatabase(FileDB.f, null); 
        else
        	mysql = myOpenHelper.getWritableDatabase(); 
   
        //��xml�ļ������Preference��
        addPreferencesFromResource(R.xml.preferences);
        //��ȡ����Preference
        updateFrequencyKey = getResources().getString(R.string.auto_update_frequency_key);
        strclassno =  getResources().getString(R.string.class_no_key);
        strclassdetail = getResources().getString(R.string.classdetail_key);
        strclassname=getResources().getString(R.string.class_name);
        strclassaddress=getResources().getString(R.string.class_address);
        
        updateFrequencyListPref = (ListPreference)findPreference(updateFrequencyKey);
        classno =(ListPreference)findPreference(strclassno);
        classdetail=(PreferenceCategory)findPreference(strclassdetail);
        classname=(EditTextPreference)findPreference(strclassname);
        classaddress=(EditTextPreference)findPreference(strclassaddress);
        
        //Ϊ����Preferenceע������ӿ�    
        updateFrequencyListPref.setOnPreferenceClickListener(this); 
        updateFrequencyListPref.setOnPreferenceChangeListener(this);
        classno.setOnPreferenceChangeListener(this);
        classno.setOnPreferenceClickListener(this);
        classname.setOnPreferenceChangeListener(this);
        classaddress.setOnPreferenceClickListener(this);
        classaddress.setOnPreferenceChangeListener(this);

        
//        Intent intent=getIntent();
//        System.out.println(intent.getExtras());
//        System.out.println(intent.getExtras().get("sqlbean"));
//        SQLiteDatabase mysql=((SqlBean)intent.getExtras().get("sqlbean")).mysql;
//        System.out.println(mysql.isOpen());
//        
        
        
        
//        Cursor cur = mysql.rawQuery("SELECT * FROM schdeule2 "
//		           , null);
//		         if (cur != null) {
//		          String temp = "";
//		          int i = 0;
//		          while (cur.moveToNext()) {//ֱ������false˵�����е�������ĩβ
//		           temp += cur.getString(0); // ����0 ָ�����е��±�,�����0ָ����id��
//		           temp += cur.getString(1);// �����0����ڵ�ǰӦ�������ǵ�text����
//		           i++;
//		           temp += "  "; // ��������������ʾ��ʽ ,�Ǻ�~
//		           if (i % 3 == 0) // ��������������ʾ��ʽ ,�Ǻ�~
//		            temp += "\n";// ��������������ʾ��ʽ ,�Ǻ�~
//		          }
//		          classaddress.setSummary(temp);
//		         }
//        
//        
              
    }
	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		Log.v("Key_SystemSetting", preference.getKey());
		//�ж����ĸ�Preference�������
	   if(preference.getKey().equals(updateFrequencyKey))
		{
			Log.v("SystemSetting", "list preference is clicked");
		}
		else
		{
			return false;
		}
		return true;
	}
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		Log.v("Key_SystemSetting", preference.getKey());
		//�ж����ĸ�Preference�ı���
		 if(preference.getKey().equals(updateFrequencyKey))
		{
			// if(((String) newValue).trim().equals(""))  
//			 switch(((String) newValue).trim())
//			 {
//			 
//			 case "����һ":
//				 day=1;break;
//			 case "���ڶ�":
//				 day=2;break;
//			 case "������":
//				 day=3;break;
//			 case "������":
//				 day=4;break;
//			 case "������":
//				 day=5;break;
//			 case "������":
//				 day=6;break;
//			 case "������":
//				 day=0;break;
//			 }
			 if(((String) newValue).trim().equals("����һ"))day=1;
			 else if(((String) newValue).trim().equals("���ڶ�"))day=2;
			 else if(((String) newValue).trim().equals("������"))day=3;
			 else if(((String) newValue).trim().equals("������"))day=4;
			 else if(((String) newValue).trim().equals("������"))day=5;
			 else if(((String) newValue).trim().equals("������"))day=6;
			 else if(((String) newValue).trim().equals("������"))day=0;

			 
				          preference.setSummary(((String) newValue).trim());
				          if(classno.isEnabled()){    
				        	  Cursor cur = mysql.rawQuery("SELECT * FROM classes WHERE day="+day+" AND no="+no+";",null);
					        if (cur != null) {
				   		          String temp = "";
				   		          String temp1="";
				   		          while (cur.moveToNext()) {//ֱ������false˵�����е�������ĩβ
				   		           temp = cur.getString(2); // ����0 ָ�����е��±�,�����0ָ����id��
				   		           temp1 = cur.getString(3);
//				   		           if (i % 3 == 0)
//				   		            temp += "\n";// ������ʾ��ʽ 
				   		          }
				   		        classname.setSummary(temp);
				   		        classname.setText(temp);
				   		     classaddress.setSummary(temp1);
				   		     classaddress.setText(temp1);
				   		         }
					        
				          }
				          classno.setEnabled(true);
				             

		}
		else  if(preference.getKey().equals(strclassno))
		{
			
			 if(((String) newValue).trim().equals("1-2"))no=1;
			 else if(((String) newValue).trim().equals("3-4"))no=2;
			 else if(((String) newValue).trim().equals("5-6"))no=3;
			 else if(((String) newValue).trim().equals("7-8"))no=4;
			 else if(((String) newValue).trim().equals("9-10"))no=5;
			 else if(((String) newValue).trim().equals("11-12"))no=6;
			
			   preference.setSummary(((String) newValue).trim());  
			//   sqlhelper.onSelect(mysql, day, no);
		        Cursor cur = mysql.rawQuery("SELECT * FROM classes WHERE day="+day+" AND no="+no+";",null);
		        if (cur != null) {
	   		          String temp = "";
	   		          String temp1="";
	   		          while (cur.moveToNext()) {//ֱ������false˵�����е�������ĩβ
	   		           temp = cur.getString(2); // ����0 ָ�����е��±�,�����0ָ����id��
	   		           temp1 = cur.getString(3);
//	   		           if (i % 3 == 0)
//	   		            temp += "\n";// ������ʾ��ʽ 
	   		          }
	   		        classname.setSummary(temp);
	   		        classname.setText(temp);
	   		     classaddress.setSummary(temp1);
	   		     classaddress.setText(temp1);
	   		         }
		    	cur.close();
			   classdetail.setEnabled(true);

			
		}
		else  if(preference.getKey().equals(strclassname))
		{
			  
	      
			
			   preference.setSummary(newValue.toString()); 

		          mysql.execSQL("UPDATE classes SET name='"+newValue.toString()+"' WHERE day="+day+" AND no="+no+";");
		          Toast.makeText(Settings.this, "�γ����޸ĳɹ�", Toast.LENGTH_LONG).show();
		}
		else  if(preference.getKey().equals(strclassaddress))
		{
			
			 preference.setSummary(newValue.toString());  

			   mysql.execSQL("UPDATE classes SET address='"+newValue.toString()+"' WHERE day="+day+" AND no="+no+";");
			   Toast.makeText(Settings.this, "�ص��޸ĳɹ�", Toast.LENGTH_LONG).show();
				
		}
		else
		{
			//�������false��ʾ�������ı�
			return false;
		}
		//����true��ʾ����ı�
		 
		return true;
	}
	
//	public boolean onKeyDown(int keyCode, KeyEvent event) { 
//	 if (keyCode == KeyEvent.KEYCODE_BACK)// ���ذ��� 
//	 {			 
//		 if(mysql.isOpen())
//			 mysql.close();
//	
//	 }
//	 
//	 this.startActivity(RESULT_OK, new Intent(this, Main.class));
//	 this.finish();
//	 return true;
//    } 
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
	protected void onPause() {
		// TODO Auto-generated method stub
		if(mysql.isOpen())
 			mysql.close();
		super.onPause();
	}
	
}
