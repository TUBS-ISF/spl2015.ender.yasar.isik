import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import vocabularytrainer.core.WordList;
import vocabularytrainer.core.WordPair;
import vocabularytrainer.gui.MenuBar;
import vocabularytrainer.gui.ToolBar;

public aspect XMLExport extends Export {
	
	before() : call(vocabularytrainer.gui.ToolBar.new(..)){
		addFileExporter(new XMLFileExporter());
	}
	
	before(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar){
		JButton fileSaveAsButton = new JButton(new ImageIcon(getClass().getResource("/save-as.png")));
		fileSaveAsButton.setBorderPainted(false);
		toolBar.getToolBar().add(fileSaveAsButton);
		toolBar.getToolBar().addSeparator();
		fileSaveAsButton.addActionListener(new FileSaveAsListener(toolBar.getTableModel(), exporterList));
	}
	
	before(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setFileMenu()) && this(menuBar) {
		JMenuItem fileSaveAsItem = new JMenuItem("Vokabelliste speichern als");
		menuBar.getFileMenu().add(fileSaveAsItem);
		fileSaveAsItem.addActionListener(new FileSaveAsListener(menuBar.getTableModel(), exporterList));
		menuBar.getFileMenu().addSeparator();
	}
	
	class XMLFileExporter implements FileExporter {
		
		private String fileSuffix = "xml";
		
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

		public FileFilter getFileFilter() {
			FileFilter fileFilter = new FileNameExtensionFilter("XML-Dateiformat", fileSuffix);
			return fileFilter;
		}

		public String getFileSuffix() {
			return "." + fileSuffix;
		}
	}
}