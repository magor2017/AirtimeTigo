package bbs.sentool.izi;

import java.util.List;

import android.accessibilityservice.AccessibilityService;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {

	@Override
	public void onAccessibilityEvent(AccessibilityEvent event) {

		final int eventType = event.getEventType() ;

		final String messageViewId = "android:id/message";
	    final String buttonViewId = "android:id/button1";
	    final String buttonOKId = "android:id/button2";
	    final String ussdOptionInputId = "android:id/editText1";
	    
		AccessibilityNodeInfo rootNode = event.getSource() ;

	    String eventText = "" ;	

		//phone 1
		String codeSecret = "1414" ;

	//phone 2
//	  String codeSecret = "1010" ;

		//phone 3
	//		String codeSecret = "1212" ;
	
			//phone 4
	//		String codeSecret = "1814" ;
		//  78 646 60 08 
			
			//phone 5
	//		String codeSecret = "1212" ;
	
			//phone 6
//			String codeSecret = "4689";
	    
		

		    
		   /* if ( event.getText().toString().toLowerCase().contains("entrez") && event.getText().toString().toLowerCase().contains("votre") && event.getText().toString().toLowerCase().contains("code") && event.getText().toString().toLowerCase().contains("secret")) {
			    AccessibilityNodeInfo ussdOptionInput =null ;
			    
			    if (rootNode.getChild(0)!=null){
			    	ussdOptionInput = rootNode.getChild(0).getChild(1);
			    }
			    
			    eventText = eventText + event.getText() ;
			    				    
				ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			    ClipData clip = ClipData.newPlainText("label", codeSecret);
			    clipboard.setPrimaryClip(clip);
			    ussdOptionInput.performAction(AccessibilityNodeInfo.ACTION_PASTE);

				    List<AccessibilityNodeInfo> validButtonNodesList =
					        rootNode.findAccessibilityNodeInfosByViewId("android:id/button1");
				    
					if (!validButtonNodesList.isEmpty()) {
						validButtonNodesList.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
				    }	    
				    		    
		    }else{*/
		    	
		    	//if (!eventText.equals("[]") && !eventText.equals("[T�l�phone]")){
				    List<AccessibilityNodeInfo> cancelButtonNodesList =
					        rootNode.findAccessibilityNodeInfosByViewId("android:id/button2");
	
				    if (!cancelButtonNodesList.isEmpty()) {
				        cancelButtonNodesList.get(0).performAction(AccessibilityNodeInfo.ACTION_CLICK);
				    }	    
	
				    eventText = eventText + event.getText() ;
				    
			        performGlobalAction(GLOBAL_ACTION_BACK) ;
					if ( !eventText.toLowerCase().contains("cution du code ussd...") && !eventText.equals("[]") && !eventText.equals("[T�l�phone]")){
						Intent intent = new Intent() ;
						intent.setAction("bbs.sentool.izi.TONTOU_BI") ;
						intent.putExtra("tontoubi", eventText.toString()) ;
										
						sendBroadcast(intent) ;
					}
					//Toast.makeText(getApplicationContext(), eventText, Toast.LENGTH_LONG).show();
		    	//}

		   // }
		
	}

	@Override
	public void onInterrupt() {
		
	}

}
