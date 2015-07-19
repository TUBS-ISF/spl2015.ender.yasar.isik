import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;


public aspect JSONImport extends Import {
	
	before() : call(vocabularytrainer.gui.ToolBar.new(..)){
		addFileImporter(new JSONFileImporter());
	}
	
	class JSONFileImporter implements FileImporter {
		
		private String fileSuffix = "json";

		public WordList importFile(String filename) {
			JSONParser parser = new JSONParser();
			WordList importedWordList = new WordList();
			
			try {
				Object object = parser.parse(new FileReader(filename));
				JSONObject jsonObject = (JSONObject) object;
				Iterator<?> iterator = jsonObject.keySet().iterator();
				while(iterator.hasNext()) {
					String key = (String) iterator.next();
					importedWordList.getWordList().add(new WordPair(key, jsonObject.get(key).toString()));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return importedWordList;
		}

		public FileFilter getFileFilter() {
			FileFilter fileFilter = new FileNameExtensionFilter("JSON-Dateiformat", fileSuffix);
			return fileFilter;
		}

		public String getFileSuffix() {
			return "." + fileSuffix;
		}
	}
}