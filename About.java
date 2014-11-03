package net.iyouyu.schedule;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity{

	private Button aboutexit;
	private TextView tw;
	private TextView tw2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		aboutexit = (Button)findViewById(R.id.button1);
		aboutexit.setText(R.string.yes);
        aboutexit.setOnClickListener(new AbourexitListener());
        tw = (TextView)findViewById(R.id.textView1);
        tw.setText(R.string.aboutcontent);
        tw2 = (TextView)findViewById(R.id.textView2);
        tw2.setText(R.string.aboutweb);
        
		
	}
	class AbourexitListener implements OnClickListener{
		
		public void onClick(View v){
			finish();
			
		}
		
		
	}
 	

}
