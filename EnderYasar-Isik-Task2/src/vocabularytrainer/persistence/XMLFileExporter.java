package vocabularytrainer.persistence;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLFileExporter {
	
	private Vector<Vector<String>> wordList;
	private String filename;
	
	public XMLFileExporter(Vector<Vector<String>> wordList, String filename) {
		this.wordList = wordList;
		this.filename = filename;
	}
	
	public void exportWordList() {
		try {
			int index = 0;
			Element rootElement = new Element("wordlist");
			rootElement.setAttribute(new Attribute("name", filename));
			Document document = new Document(rootElement);
			XMLOutputter xmlOutput = new XMLOutputter();
			Vector<Element> wordPairs = new Vector<Element>();
	
			for(Vector<String> vector : wordList) {
				wordPairs.add(new Element("wordpair"));
				wordPairs.get(index).addContent(new Element("german").setText(vector.elementAt(0)));
				wordPairs.get(index).addContent(new Element("foreign").setText(vector.elementAt(1)));
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
