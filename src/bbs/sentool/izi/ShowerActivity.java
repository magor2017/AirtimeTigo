package bbs.sentool.izi;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
	
public class ShowerActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shower);
		((TextView)findViewById(R.id.textussd)).setText(getIntent().getExtras().getString("tontou-bi")) ;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shower, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//Toast.makeText(this.getApplicationContext(), "id= "+id, Toast.LENGTH_LONG).show();
		//if (id == R.id.action_settings) {
			return true;
		//}
		//return super.onOptionsItemSelected(item);
	}
}
