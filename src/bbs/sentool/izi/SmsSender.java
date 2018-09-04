package bbs.sentool.izi;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

public class SmsSender {
	//final SmsManager smsManager_Sender = SmsManager.getDefault() ;
	
	public void sendSMSDeadService(String phoneNo, Activity caller){
		//smsManager_Sender.sendTextMessage("772220594", null, "This is a test message", null, null) ;
		
		Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNo)) ;
		smsIntent.putExtra("sms_body", "This is the so anticipated message") ;
		caller.startActivity(smsIntent) ;
	}
}
