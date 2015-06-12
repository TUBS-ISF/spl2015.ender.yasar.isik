//#ifdef XMLExport
package vocabularytrainer.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;

public class XMLFileExporter implements FileExporter {
	
	public XMLFileExporter() {
	}

	public void exportFile(WordList wordList, String filename) {
		try {
			int index = 0;
			Element rootElement = new Element("wordlist");
			rootElement.setAttribute(new Attribute("name", filename));
			Document document = new Document(rootElement);
			XMLOutputter xmlOutput = new XMLOutputter();
			List<Element> wordPairs = new ArrayList<Element>();
	
			for(WordPair wordPair : wordList.getWordList()) {
				wordPairs.add(new Element("wordpair"));
				wordPairs.get(index).addContent(new Element("german").setText(wordPair.getGermanWord()));
				wordPairs.get(index).addContent(new Element("foreign").setText(wordPair.getForeignWord()));
				document.getRootElement().addContent(wordPairs.get(index));
				index++;
			}
			
			xmlOutput.setFormat(Format.getPrettyFormat());
			
			if (!filename.endsWith(".xml")) {
				xmlOutput.output(document, new FileWriter(filename + ".xml"));
			} else {
				xmlOutput.output(document, new FileWriter(filename));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
//#endif
