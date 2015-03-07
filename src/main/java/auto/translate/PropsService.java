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
	
	/**
	 * read the messages of a properties file
	 * @param sourceFile
	 */
	public void readPropsFile(File sourceFile) {
		
		TranslateService translateService=new TranslateService();
		FilesService filesService=new FilesService();
		DbService dbService=new DbService();
		
		
		log.info("File to convert:" + sourceFile.getAbsolutePath());
		
		try {
			
			dbService.openDatabase();
			
			//fail-safe code
			//check if the the current file is already translated
			String fileInDatabase=dbService.getCurrentFileName();
			if(fileInDatabase==null){
				dbService.setCurrentFileName(sourceFile);
			}else{
				if(!fileInDatabase.equals(sourceFile.getAbsolutePath())){
					return;
				}
			}
			
			//get all the keys from the properties file
			Configuration configuration = new PropertiesConfiguration(sourceFile);
			Iterator<String> keys = configuration.getKeys();
			List<String> keyList = new ArrayList<String>();
			while (keys.hasNext()) {
				keyList.add(keys.next());
			}
			
			
			for (String key : keyList) {
				
				//check if key in database (fail-safe code)
				boolean found=dbService.checkKey(key);
				
				if(!found){
					String message=configuration.getString(key);
					log.info("message to be translated : "+message);
					
					String translatedText="";
					if(!message.equals("")){ 
						//call the translation service
						translatedText=translateService.translate(message);
						log.info("translated to : "+translatedText);
						
						//convert the translated message to unicode format
						if(auto.translate.Configuration.CONVERT_TRANSLATED_MESSAGE_TO_UNICODE_FORMAT){
							translatedText=StringEscapeUtils.escapeJava(translatedText);
						}
					}
					
					//if translatedText is null then there is a problem accessing Microsoft Translator api --> exit program
					if(translatedText==null){
						dbService.closeDatase();
						System.exit(0);
					}
					
					//save the translated message into the embedded database
					dbService.setKeyMessage(key, translatedText);

				}else{
					log.info("key found in database : "+key);
				}
				
			}
			
			//save the target file
			filesService.saveTargetFile(sourceFile, dbService);
			dbService.emptyDatabase();
			dbService.closeDatase();

			

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
