package net.iyouyu.schedule;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class SystemSet extends PreferenceActivity implements OnPreferenceClickListener,
OnPreferenceChangeListener{
	private int bgnum=0;
	String bgKey,starttermkey;
	ListPreference bglp;
	Preference startterm;
	public static boolean isnew=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
		addPreferencesFromResource(R.xml.systemset);
		  bgKey = getResources().getString(R.string.bg_key);
		  starttermkey=getResources().getString(R.string.startterm_key);
		  
		  
		  bglp = (ListPreference)findPreference(bgKey);
		  startterm=(Preference)findPreference(starttermkey);
		  
		  
		  bglp.setOnPreferenceClickListener(this); 
		  bglp.setOnPreferenceChangeListener(this);
		  startterm.setOnPreferenceClickListener(this); 
		  startterm.setOnPreferenceChangeListener(this);
	}

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		 if(preference.getKey().equals(bgKey))
			{
				 if(((String) newValue).trim().equals("水花"))bgnum=1;
				 else if(((String) newValue).trim().equals("星空"))bgnum=2;
				 else if(((String) newValue).trim().equals("海岸-明"))bgnum=3;
				 else if(((String) newValue).trim().equals("海岸-暗"))bgnum=4;
				// System.out.println(((String) newValue).trim());
					preference.setSummary(((String) newValue).trim());
				  isnew=true;
			       SharedPreferences share = getSharedPreferences("schedule.xm",Context.MODE_PRIVATE);  
			       Editor editor = share.edit();
			       editor.putInt("bgnum", bgnum);
			     //  editor.putBoolean("create", true);
			       editor.commit();  
			       Toast.makeText(SystemSet.this, "背景更改", Toast.LENGTH_LONG).show();


			}
		else return false;
		return true;
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		 if(preference.getKey().equals(starttermkey)){
				Intent intent = new Intent();
				intent.setClass(SystemSet.this, TermSet.class);
				SystemSet.this.startActivity(intent);
			}
		 else 
			 return false;
		 return true;
	}
	
	
}