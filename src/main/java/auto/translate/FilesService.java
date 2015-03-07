package auto.translate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.Logger;

public class FilesService {
	
	static Logger log = Logger.getLogger(FilesService.class.getName());
	
	private final File propsSourceDir=new File(Configuration.PROPERTIES_SOURCE_FOLDER);
	private final File propsTargetDir=new File(Configuration.GENERATED_PROPERTIES_FOLDER);
	
	
	/**
	 * read the properties files from the source folder
	 * @return List<File>
	 */
	public List<File> searchPropsFiles(){
		List<File>  propsSourcefiles =(List<File>)  FileUtils.listFiles(
				propsSourceDir, 
				new RegexFileFilter(Configuration.PROPERTIES_NAMES_REGEX), 
				DirectoryFileFilter.DIRECTORY);		
		
		return propsSourcefiles;		
	}
	
	/**
	 * construct and save the target file
	 * @param sourceFile
	 * @param dbService
	 */
	public void saveTargetFile(File sourceFile, DbService dbService){
		
		List<String> messages=new ArrayList<String>();
		
		ConcurrentNavigableMap<String,String> map=dbService.getAllMessages();
		
		for(String key:map.keySet()){
			String translatedText=map.get(key);
			messages.add(key+"="+translatedText);
		}	
		
		
		String fileName=sourceFile.getName();
		
		File parentFolder=sourceFile.getParentFile();
		String parentFolderPath=parentFolder.getAbsolutePath();
		
		String targetFolderPath=parentFolderPath.replace(propsSourceDir.getAbsolutePath(), propsTargetDir.getAbsolutePath());
		String targetFileName=fileName.replace(".properties", Configuration.TRANSLATION_JAVA_LOCALE_CODE + ".properties");
		
		String targetFullPath=targetFolderPath+File.separator+targetFileName;
		
		File targetFile = new File(targetFullPath);
		
		log.info("Target file will be saved in : " + targetFile);
		
		targetFile.getParentFile().mkdirs();
		
		try {
			FileUtils.writeLines(targetFile, "UTF-8", messages);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//rename the source file
		String fullPathSourceName=sourceFile.getAbsolutePath();
		File translatedSourceFile=new File(fullPathSourceName+".translated");
		sourceFile.renameTo(translatedSourceFile);
		

		
	}
}
