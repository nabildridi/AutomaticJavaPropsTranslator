package auto.translate;

import com.memetix.mst.language.Language;

public interface Configuration {
	
	//Path of the source folder containing the properties files to be translated.
	public final String PROPERTIES_SOURCE_FOLDER="source";
	
	//Regex expression for source files names to lookup after, by default it will process all files with 
	//'properties' extension found in the source folder.
	public final String PROPERTIES_NAMES_REGEX=".*.properties";
	
	
	//Path of the target folder, the translated files will be stored in this location.
	public final String GENERATED_PROPERTIES_FOLDER="generated";
	
	
	//Target language code, will be added in the translated file name, for example '_ar' or '_fr_FR' so the 
	//target file will be 'sample_ar.properties' or 'sample_fr_FR.properties'.
	public final String TRANSLATION_JAVA_LOCALE_CODE="_ar";
	
	
	//Source language, you can use the value 'Language.AUTO_DETECT' if you are not sure about 
	//the source language.
	public final Language MESSAGE_SOURCE_LANGUAGE=Language.ENGLISH;
	
	
	//Target language.
	public final Language MESSAGE_TARGET_LANGUAGE=Language.ARABIC;	
	
	
	//Boolean, if true, the translated message will be converted and written in the Unicode format.
	public final boolean CONVERT_TRANSLATED_MESSAGE_TO_UNICODE_FORMAT=true;
	
	
	//Client id to access Microsoft translator API.
	public final String MICROSOFT_CLIENT_ID="XYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZ";
	
	//Client secret to access Microsoft translator API.
	public final String MICROSOFT_CLIENT_SECRET="XYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZ";
	
}
