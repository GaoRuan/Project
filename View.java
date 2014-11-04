package net.iyouyu.schedule;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;

public class View extends PreferenceActivity implements OnPreferenceClickListener,
OnPreferenceChangeListener{
	
	
	  public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //ŽÓxmlÎÄŒþÖÐÌíŒÓPreferenceÏî
	        addPreferencesFromResource(R.xml.viewpreferences);
	  }
	
	
	
	
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		return false;
	}

}
