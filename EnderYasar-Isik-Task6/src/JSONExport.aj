import java.io.FileWriter;
import java.io.IOException;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONObject;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;


public aspect JSONExport extends Export {
	
	before() : call(vocabularytrainer.gui.ToolBar.new(..)){
		addFileExporter(new JSONFileExporter());
	}
	
	class JSONFileExporter implements FileExporter {
		
		private String fileSuffix = "json";
		
		@SuppressWarnings("unchecked")
		public void exportFile(WordList wordList, String filename) {
			JSONObject jsonObject = new JSONObject();
			for(WordPair wordPair : wordList.getWordList()) {
				jsonObject.put(wordPair.getGermanWord(), wordPair.getForeignWord());
			}
			try {
				FileWriter fileWriter = null;
				if (!filename.endsWith(".json")) {
					fileWriter = new FileWriter(filename + ".json");
				} else {
					fileWriter = new FileWriter(filename);
				}
				fileWriter.write(jsonObject.toJSONString());
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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