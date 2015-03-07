package auto.translate;

import com.memetix.mst.language.Language;

public interface Configuration {
	
	public final String PROPERTIES_SOURCE_FOLDER="source";
	public final String PROPERTIES_NAMES_REGEX=".*.properties";
	
	
	
	public final String GENERATED_PROPERTIES_FOLDER="generated";	
	public final String TRANSLATION_JAVA_LOCALE_CODE="_ar";
	
	
	
	public final Language MESSAGE_SOURCE_LANGUAGE=Language.ENGLISH;
	public final Language MESSAGE_TARGET_LANGUAGE=Language.ARABIC;	
	
	public final boolean CONVERT_TRANSLATED_MESSAGE_TO_UNICODE_FORMAT=true;
	
	public final String MICROSOFT_CLIENT_ID="XYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZ";
	public final String MICROSOFT_CLIENT_SECRET="XYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZXYZ";
	
	

	
}
