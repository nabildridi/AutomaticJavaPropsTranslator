package auto.translate;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

public class Main {
	
	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		FilesService filesService=new FilesService();
		PropsService propsService=new PropsService();
		
		//read the properties files from the source folder
		List<File>  propsSourcefiles =filesService.searchPropsFiles();
		log.info("Number of files to translate : " + propsSourcefiles.size());
		
		//read the content on the properties files one by one
		for(File f:propsSourcefiles){
			propsService.readPropsFile(f);
		}
		
		
		
		
		
		
	}

}
