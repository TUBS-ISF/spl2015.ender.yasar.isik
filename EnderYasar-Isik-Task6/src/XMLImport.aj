import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;
import vocabularytrainer.gui.MenuBar;
import vocabularytrainer.gui.ToolBar;

public aspect XMLImport extends Import {
	
	before() : call(vocabularytrainer.gui.ToolBar.new(..)){
		Import.addFileImporter(new XMLFileImporter());
	}
	
	before(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar) {
		JButton fileOpenButton = new JButton(new ImageIcon(getClass().getResource("/folder.png")));
		fileOpenButton.setBorderPainted(false);
		toolBar.getToolBar().add(fileOpenButton);
		fileOpenButton.addActionListener(new FileOpenListener(toolBar.getTableModel(), importerList));
	}
	
	before(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setFileMenu()) && this(menuBar) {
		JMenuItem fileOpenItem = new JMenuItem("Vokabelliste öffnen");
		menuBar.getFileMenu().add(fileOpenItem);
		fileOpenItem.addActionListener(new FileOpenListener(menuBar.getTableModel(), importerList));
	}
	
	class XMLFileImporter implements FileImporter {
		
		private String fileSuffix = "xml";
		
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

		public FileNameExtensionFilter getFileFilter() {
			return new FileNameExtensionFilter("XML-Dateiformat", fileSuffix);
		}

		public String getFileSuffix() {
			return "." + fileSuffix;
		}
	}
}