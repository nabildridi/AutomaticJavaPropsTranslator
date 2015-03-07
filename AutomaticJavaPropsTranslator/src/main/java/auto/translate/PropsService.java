package auto.translate;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;

public class PropsService {
	
	static Logger log = Logger.getLogger(PropsService.class.getName());
	
	public void readPropsFile(File sourceFile) {
		
		TranslateService translateService=new TranslateService();
		FilesService filesService=new FilesService();
		DbService dbService=new DbService();
		
		
		log.info("File to convert:" + sourceFile.getAbsolutePath());
		
		try {
			
			dbService.openDatabase();
			String fileInDatabase=dbService.getCurrentFileName();
			if(fileInDatabase==null){
				dbService.setCurrentFileName(sourceFile);
			}else{
				if(!fileInDatabase.equals(sourceFile.getAbsolutePath())){
					return;
				}
			}
			
			Configuration configuration = new PropertiesConfiguration(sourceFile);

			Iterator<String> keys = configuration.getKeys();
			List<String> keyList = new ArrayList<String>();
			while (keys.hasNext()) {
				keyList.add(keys.next());
			}
			
			for (String key : keyList) {
				
				//check if key in database
				boolean found=dbService.checkKey(key);
				
				if(!found){
					String message=configuration.getString(key);
					log.info("message to be translated : "+message);
					
					String translatedText="";
					if(!message.equals("")){ 
						translatedText=translateService.translate(message);
						log.info("translated to : "+translatedText);
						
						if(auto.translate.Configuration.CONVERT_TRANSLATED_MESSAGE_TO_UNICODE_FORMAT){
							translatedText=StringEscapeUtils.escapeJava(translatedText);
						}
					}
					
					if(translatedText==null){
						dbService.closeDatase();
						System.exit(0);
					}
					
					dbService.setKeyMessage(key, translatedText);
					
					Thread.sleep(500);
				}else{
					log.info("key found in database : "+key);
				}
				
			}
			

			filesService.saveTargetFile(sourceFile, dbService);
			dbService.emptyDatabase();
			dbService.closeDatase();

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
