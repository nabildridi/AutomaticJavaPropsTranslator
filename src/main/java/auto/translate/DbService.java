package auto.translate;

import java.io.File;
import java.util.concurrent.ConcurrentNavigableMap;

import org.mapdb.DB;
import org.mapdb.DBMaker;


public class DbService {
	
	private DB db;
	
	public void openDatabase(){
		this.db = DBMaker.newFileDB(new File("failureRecovery"))
                .closeOnJvmShutdown()
                .make();	
	}
	
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
	
	public void setKeyMessage(String key, String translatedText){		
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("messages");
		map.put(key, translatedText);
		db.commit();		
	}
	
	public ConcurrentNavigableMap<String, String> getAllMessages(){
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("messages");
		return map;
	}
	
	public void emptyDatabase(){
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("propertyFile");
		map.clear();
		
		ConcurrentNavigableMap<String,String> map1 = db.getTreeMap("messages");
		map1.clear();
		
		db.commit();		
	}
	
	
	public boolean checkKey(String key){
		ConcurrentNavigableMap<String,String> map = db.getTreeMap("messages");
		String arabicMessage=map.get(key);
		
		if(arabicMessage==null){
			return false;
		}else{
			return true;
		}
	
	}
	
	public void closeDatase(){
		this.db.close();
	}

	
	public DB getDb() {
		return db;
	}

	
	
}
