package bbs.sentool.izi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UssdBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		MainActivity mainActivity = ((AirtimeTigoApp)context.getApplicationContext()).mainActivity ;		
		mainActivity.yeurTontouBi(intent.getExtras().getString("tontoubi")) ;
	}

}
