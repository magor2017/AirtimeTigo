package bbs.sentool.izi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSBroadCastReceiver extends BroadcastReceiver {

	final SmsManager smsManager = SmsManager.getDefault() ;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		final Bundle bundle = intent.getExtras() ;
		MainActivity mainActivity = ((AirtimeTigoApp)context.getApplicationContext()).mainActivity ;		

		try{
			if (bundle != null){
				final Object[] pdusObj = (Object[]) bundle.get("pdus") ;
				
				for (int i=0 ; i<pdusObj.length ; i++) {
					SmsMessage currentMessage = SmsMessage.createFromPdu( (byte[])pdusObj[i] ) ;
					String senderNum = currentMessage.getDisplayOriginatingAddress() ;
					String message = currentMessage.getDisplayMessageBody() ;
										
					if (senderNum.contains("OrangeMoney") && message.contains("Retrait")){
//						mainActivity.handleRetraitMessage(message) ;
					}
				}
			}
		}catch(Exception e){
			//Toast.makeText(context, "An error has occurred!", Toast.LENGTH_SHORT).show() ;
		}
	}

}
