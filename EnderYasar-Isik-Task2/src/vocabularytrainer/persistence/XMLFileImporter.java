package vocabularytrainer.persistence;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLFileImporter {
	
	private String filename;
	
	public XMLFileImporter(String filename) {
		this.filename = filename;
	}
	
	public Vector<Vector<String>> importWordList() {
		int index = 0;
		Vector<Vector<String>> wordList = new Vector<Vector<String>>();
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);

		try {
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List<Element> list = rootNode.getChildren("wordpair");
			
			for(Element element : list) {
				wordList.add(new Vector<String>());
				wordList.get(index).add(element.getChildText("german"));
				wordList.get(index).add(element.getChildText("foreign"));
				index++;
			}
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		return wordList;
	}
	
}
