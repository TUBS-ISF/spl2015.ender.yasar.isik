package vocabularytrainer.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.opencsv.CSVWriter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;

public aspect CSVExport extends Export {
	
	before() : call(public vocabularytrainer.gui.controller.FileSaveAsListener.new(..)){
		super.setExporterList(this);
	}
	
	private String fileSuffix = "csv";
	
	public CSVExport() {}

	public void exportFile(WordList wordList, String filename) {
		try {
			CSVWriter csvWriter = new CSVWriter(new FileWriter(filename));
			csvWriter.writeAll(convertWordListToListOfStringArrays(wordList));
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private List<String[]> convertWordListToListOfStringArrays(WordList wordList) {
		List<String[]> list = new ArrayList<String[]>();
		
		for(WordPair wordPair : wordList.getWordList()) {
			list.add(new String[] {wordPair.getGermanWord(), wordPair.getForeignWord()});
		}
		
		return list;
	}

	public FileFilter getFileFilter() {
		FileFilter fileFilter = new FileNameExtensionFilter("CSV-Dateiformat", fileSuffix);
		return fileFilter;
	}

	public String getFileSuffix() {
		return "." + fileSuffix;
	}

}
