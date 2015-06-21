package vocabularytrainer.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;

public class XMLFileImporter implements FileImporter {
		
	public XMLFileImporter() {
		
	}

	public WordList importFile(String filename) {
		int index = 0;
		WordList wordList = new WordList();
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);

		try {
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<Element> list = rootNode.getChildren("wordpair");
			
			for(Element element : list) {
				wordList.getWordList().add(new WordPair());
				wordList.getWordList().get(index).setGermanWord(element.getChildText("german"));
				wordList.getWordList().get(index).setForeignWord(element.getChildText("foreign"));
				index++;
			}
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return wordList;
	}

	public FileNameExtensionFilter getFileNameExtensionFilter() {
		return new FileNameExtensionFilter("XML-Dateiformat", "xml");
	}

	public String getFileSuffix() {
		return ".xml";
	}
	
}
