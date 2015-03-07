package auto.translate;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;

public class Main {
	
	static Logger log = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		FilesService filesService=new FilesService();
		PropsService propsService=new PropsService();
		
		List<File>  propsSourcefiles =filesService.searchPropsFiles();
		log.info("Number of files to translate : " + propsSourcefiles.size());
		for(File f:propsSourcefiles){
			propsService.readPropsFile(f);
		}
		
		
		
		
		
		
	}

}
