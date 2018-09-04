package bbs.sentool.izi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.support.v7.app.ActionBarActivity;
import android.app.Application;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements OnClickListener{

	//phone 1
	String codeSecret = "1414" ;

//phone 2
//  String codeSecret = "1010" ;

//phone 3
//	String codeSecret = "1212" ;

	//phone 4
//	String codeSecret = "1814" ;
//  78 646 60 08 
	
	//phone 5
//	String codeSecret = "1212" ;

	//phone 6
//	String codeSecret = "4689";
	

	ProgressDialog progressDialog;	
	UssdBroadcastReceiver ussdBCReceiver ;
	SMSBroadCastReceiver smsBCReceiver;
	IntentFilter intentFilter, smsIntentFilter ;
	TextView tView ;
	String ussdCode, resultTamp ;
	
	int validatedRetrait = 0 ; 
	int handledRetrait = 0 ;

	int xaarGui = 0 ;
	
	ArrayList<Retrait> retraitsInQueue = new ArrayList<Retrait>() ;
   

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.activity_main);
        }catch(Exception e){Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();}
        startService(new Intent(this, MyAccessibilityService.class)) ;
        tView = (TextView)findViewById(R.id.textView1) ;

        ((Button)findViewById(R.id.testnetwork)).setOnClickListener(this) ;
       
        
       // IziApp ussdHandlerApp = (IziApp)getApplicationContext() ;
        AirtimeTigoApp ussdHandlerApp = (AirtimeTigoApp)getApplicationContext() ;
        ussdHandlerApp.mainActivity = this ;
        
        ussdBCReceiver = new UssdBroadcastReceiver();
        intentFilter = new IntentFilter("bbs.sentool.izi.TONTOU_BI");

        smsBCReceiver = new SMSBroadCastReceiver();
        smsIntentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        progressDialog = new ProgressDialog(MainActivity.this);

    }

  //3776-1983-1449-1130-8722
    /*
       @Override
       public boolean onCreateOptionsMenu(Menu menu) {
           getMenuInflater().inflate(R.menu.main, menu);
           return true;
       }

       @Override
       public boolean onOptionsItemSelected(MenuItem item) {
           int id = item.getItemId();
           if (id == R.id.action_settings) {
               return true;
           }
           return super.onOptionsItemSelected(item);
       }
*/
   	private void dailNumber(String incomingReq) {    

   		ussdCode = this.getUssdCode(incomingReq) ;
   		//ussdCode = "#117*779013878*100*1*1917#";
   		//votre demande est en cours de traitement veuillez patienter SVP
   		//Toast.makeText(this,"my toast test", Toast.LENGTH_LONG).show();
   	
   		//if(this.resultTamp.contains("R")){
   			//ussdCode = ussdCode.substring(0, ussdCode.length()-1).concat("*1#") ;
   		//}
   		
   	    Intent intent = new Intent(Intent.ACTION_CALL);	
   	    intent.setData(ussdToCallableUri(ussdCode));
   	    try{
   	        startActivity(intent);
   	    } catch (SecurityException e){
   	        e.printStackTrace();
   	    }	
   	}
   //moustapha2/12345
   	private Uri ussdToCallableUri(String ussd) {

   	    String uriString = "";

   	    if(!ussd.startsWith("tel:"))
   	        uriString += "tel:";

   	    for(char c : ussd.toCharArray()) {

   	        if(c == '#')
   	            uriString += Uri.encode("#");
   	        else
   	            uriString += c;
   	    }

   	    return Uri.parse(uriString);
   	}	
   	
     //ANP2JXS5
   	private String getUssdCode(String incoming){
   		String[] codeBlocks  = incoming.split("/") ;
   		//#177*numclient*code*montant#
   		String ussdCode = "#177*"+codeBlocks[0]+"*1917*"+codeBlocks[1]+"#" ;

   		/* seddo */
   		
   		((TextView) findViewById(R.id.textView1)).setText("Executed code : "+ussdCode ) ;
   		return ussdCode ;
   	}
       
       
   	@Override
   	public void onClick(View arg0) {
   		//dailNumber("762750179/100");
   		new RemoteCaller().execute("take") ;
   	}
   	public class RemoteCaller extends AsyncTask<String, Integer, String>{

   		String requestedInfo = "";
//   		String wSBaseUrl="http://51.254.200.129/backendprod/horsSentiersBattus/scripts/omr.php?pn=4" ;

   		String wSBaseUrl="http://51.254.200.129/backendprod/horsSentiersBattus/scripts/airtime/izi/iziposter.php" ;

   		@Override
   		protected String doInBackground(String... params) {

   			List<NameValuePair> parametres = new ArrayList<NameValuePair>();
   			//parametres.add(new BasicNameValuePair("pn","1"));

   			String switcher = params[0];
   			
   			if (switcher.equals("success")){
   				parametres.add(new BasicNameValuePair("ok", "1"));
   			}

   			if (switcher.contains("-")){
   				parametres.add(new BasicNameValuePair("ok", switcher));
   			}
   						
   			
   			
   			try {
   				String url = wSBaseUrl ;
   /**************/
   						
   				HttpClient client = new DefaultHttpClient();
   				HttpPost httpPost = new HttpPost(url);
   				UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parametres);
   				httpPost.setEntity(formEntity);
   			
   				
   				while(!isConnected()){
   					xaarGui++ ;
   					Thread.sleep(1000) ;
   				}
   				
   				HttpResponse response = client.execute(httpPost);
   		        EntityUtils EntUti;
   		        String returnedString = EntityUtils.toString(response.getEntity());
   				
   /**************/				
   	        
   		        return returnedString;
   			 } catch (Exception e) {
   			   }
   	       return null;			
   		}

   		@Override
   		protected void onPostExecute(String result){
   			progressDialog.cancel();
   			displayServerResponse(result);
   		 }
   		
   		@Override
   		protected void onPreExecute(){
   			super.onPreExecute();
   			progressDialog.setTitle("Compt��l ...");
   			progressDialog.show();
   		}
   	}

   	public void yeurTontouBi(String switcher){

//           unregisterReceiver(ussdBCReceiver) ;
           String qualification = qualifieReponse(switcher) ;
   		if ( qualification.equals("success") ){
   			
   				new RemoteCaller().execute("success") ;
   			
   		}else{
   			tView.append("Bad request, khoolaatal linga diokh syst�m bi ak limoula tontou nii ..."+
   			switcher.substring(0, switcher.indexOf(", ")));
   			new RemoteCaller().execute(qualification) ;
   		}
   	}
   	//votre demande est en cours de traitement veuillez patienter SVP
   	public String qualifieReponse(String incomingSwitcher){
   		String switcher = incomingSwitcher ;
   		
   		if ( switcher.toLowerCase().contains("vus recevrez bientot")
   				&& switcher.toLowerCase().contains("le message")
   				&& switcher.toLowerCase().contains("par sms") )
   			return "success";
   		if ( switcher.toLowerCase().contains("vous recevrez bientot")
   				&& switcher.toLowerCase().contains("le message")
   				&& switcher.toLowerCase().contains("par sms") )
   			return "success";

   		if ( switcher.toLowerCase().contains("desole")
   				&& switcher.toLowerCase().contains("solde") 
   				&& switcher.toLowerCase().contains("insuffisant")) 
   			return "-3";
         
   		
   		return "-7" ;

   	}
   	
       public void displayServerResponse(String result){
       	
	       	if (!result.contains("/")){
	       		new RemoteCaller().execute("take") ;
	       	}
	       	else{
	           	this.resultTamp = result ;    	
	            registerReceiver(ussdBCReceiver, intentFilter) ;
	             this.dailNumber(result) ;  
	       	 }
      
       }

   
       public boolean isConnected(){
       	    ConnectivityManager cm =
       	        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
       	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
       	    return (netInfo!=null && netInfo.isConnected());
       }
       
//       @Override
//       public void onStop() {
//           Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.breaker");
//           Toast.makeText(this, "onStop Called", Toast.LENGTH_SHORT) ;
//           launchIntent.addCategory(Intent.CATEGORY_LAUNCHER) ;
//           startActivity(launchIntent);
//           super.onStop();
//       }
   //
   //   @Override
//       public void onDestroy() {
//           Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.breaker");
//           Toast.makeText(this, "onDestroy Called", Toast.LENGTH_SHORT) ;
//           launchIntent.addCategory(Intent.CATEGORY_LAUNCHER) ;
//           startActivity(launchIntent);
//       }

    
}
