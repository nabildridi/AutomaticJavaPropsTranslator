# Automatic Java properties files translator
The purpose of this project is to translate the messages of a big number of i18n java properties files from one language to another in automatic fashion using Microsoft Bing translation API, this program has some benefits :

1. It will translate every message from each provided properties file, create the correspondent file with the same keys but with the translated messages.
2. Fail-safe : you can stop the execution, when the program restart, it will continue where it stopped.
3. It will recreate exactly the same folders structure under the source folder into the target folder.

## Execution :
This project is an Eclipse project, please run the class 'auto.translate.Main'.

## Configuration :
You can configure the program by the changing the values in the interface class : auto.translate.Configuration

The configuration fields are :

	PROPERTIES_SOURCE_FOLDER  : Path of the source folder containing the properties files to be translated.
	PROPERTIES_NAMES_REGEX    : Regex expression for source files names to lookup after, by default it will process all files with 'properties' extension found in the source folder.
&nbsp;

	GENERATED_PROPERTIES_FOLDER  : Path of the target folder, the translated files will be stored in this location.
	TRANSLATION_JAVA_LOCALE_CODE : Target language code, will be added in the translated file name, for example '_ar' or '_fr_FR' so the target file will be 'sample_ar.properties' or 'sample_fr_FR.properties'.
&nbsp;

	MESSAGE_SOURCE_LANGUAGE : Source language, you can use the value 'Language.AUTO_DETECT' if you are not sure about the source language.
	MESSAGE_TARGET_LANGUAGE : Target language.
&nbsp;

	CONVERT_TRANSLATED_MESSAGE_TO_UNICODE_FORMAT : Boolean, if true, the translated message will be converted and written in the Unicode format.
&nbsp;

	MICROSOFT_CLIENT_ID     : Client id to access Microsoft translator API. 
	MICROSOFT_CLIENT_SECRET : Client secret to access Microsoft translator API.
	
**Please refer to this [video](http://wordfast.fi/blog/cat-tools/2012/05/01/new-microsoft-translator-api-with-client-id-and-client-secret/) to understand how to get a client id and secret after registration in Microsoft Azure marketplace (it's a free but limited offer)**


## list of supported languages in Microfost Translator as listed in the excellent [microsoft-translator-java-api](https://github.com/boatmeme/microsoft-translator-java-api)

    AUTO_DETECT
    ARABIC
    BULGARIAN
    CATALAN
    CHINESE_SIMPLIFIED
    CHINESE_TRADITIONAL
    CZECH
    DANISH
    DUTCH
    ENGLISH
    ESTONIAN
    FINNISH
    FRENCH
    GERMAN
    GREEK
    HAITIAN_CREOLE
    HEBREW
    HINDI
    HMONG_DAW
    HUNGARIAN
    INDONESIAN
    ITALIAN
    JAPANESE
    KOREAN
    LATVIAN
    LITHUANIAN
    MALAY
    NORWEGIAN
    PERSIAN
    POLISH
    PORTUGUESE
    ROMANIAN
    RUSSIAN
    SLOVAK
    SLOVENIAN
    SPANISH
    SWEDISH
    THAI
    TURKISH
    UKRAINIAN
    URDU
    VIETNAMESE
	 
	 

