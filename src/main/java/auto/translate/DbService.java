package auto.translate;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;


public class DbService {
	
	private DB db;
	
	/**
	 * Open embedded database
	 */
	public void openDatabase(){
		this.db = DBMaker.newFileDB(new File("failureRecovery"))
                .closeOnJvmShutdown()
                .make();	
	}
	
	/**
	 * Save the file name of the cuurent properties file
	 * @param file
	 */
	public void setCurrentFileName(File file){		
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("propertyFile");
		map.put("file", file.getAbsolutePath());
		db.commit();		
	}
	
	public String getCurrentFileName(){		
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("propertyFile");
		String fileName=map.get("file");
		return fileName;		
	}
	
	/**
	 * Save the translated message
	 * @param key
	 * @param translatedText
	 */
	public void setKeyMessage(String key, String translatedText){		
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("messages");
		map.put(key, translatedText);
		db.commit();		
	}
	
	/**
	 * Get all the translated messages stored in the embedded database
	 * @return
	 */
	public ConcurrentNavigableMap<String, String> getAllMessages(){
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("messages");
		return map;
	}
	
	/**
	 * empty embedded database
	 */
	public void emptyDatabase(){
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("propertyFile");
		map.clear();
		
		ConcurrentNavigableMap<String,String> map1 = db.getTreeMap("messages");
		map1.clear();
		
		db.commit();		
	}
	
	/**
	 * Check is the key of the message before the translation
	 * @param key
	 * @return
	 */
	public boolean checkKey(String key){
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("messages");
		String translatedMessage=map.get(key);
		
		if(translatedMessage==null){
			return false;
		}else{
			return true;
		}
	
	}
	
	/**
	 * Close embedded database
	 */
	public void closeDatase(){
		this.db.close();
	}

	
	public DB getDb() {
		return db;
	}

	
	
}
