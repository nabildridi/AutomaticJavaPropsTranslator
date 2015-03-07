package auto.translate;

import org.apache.log4j.Logger;

import com.memetix.mst.translate.Translate;



public class TranslateService {
	
	static Logger log = Logger.getLogger(TranslateService.class.getName());
	
	public String translate(String message){
		String translatedText=null;
		
		Translate.setClientId(Configuration.MICROSOFT_CLIENT_ID);
	    Translate.setClientSecret(Configuration.MICROSOFT_CLIENT_SECRET);
	    
	   for(int i=0;i<10;i++){
		    try {
				translatedText = Translate.execute(message, Configuration.MESSAGE_SOURCE_LANGUAGE, Configuration.TARGET_LANGUAGE);
			} catch (Exception e) {e.printStackTrace();}
		    
		    if(translatedText!=null && !translatedText.startsWith("TranslateApiException: Cannot find an active Azure Market Place Translator")){
		    	break;		    	
		    }else{
		    	translatedText =null;
		    	log.info("bad translation!!!!!!!!!!!!!!!!!!");
		    }
		    
		    try {Thread.sleep(500);} catch (InterruptedException e) {}
	   }

	   return translatedText;
	}

}
