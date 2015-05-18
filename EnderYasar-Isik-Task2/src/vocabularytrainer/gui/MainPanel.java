package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import vocabularytrainer.core.ProductLineConfiguration;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MainPanel {
	
	private JPanel listPanel;
	private JTable wordListTable;
	private DefaultTableModel tableModel;
	private JComboBox<String> orderComboBox, directionComboBox, typeComboBox;
	
	public MainPanel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
		listPanel = new JPanel(new MigLayout());
		
		listPanel.add(getTablePanel(), new CC().dockWest().gapX("0", "5").gapY("5", "5"));
//		listPanel.add(getModifyListPanel(), new CC().dockNorth().gapX("10", "10").gapY("10", "10"));
//		listPanel.add(getSearchPanel(), new CC().dockNorth().gapX("10", "10").gapY("10", "10"));
		listPanel.add(getConfigurationPanel(), new CC().dockNorth().gapX("5", "0").gapY("0", "0"));
		listPanel.add(getStartPracticePanel(), new CC().dockNorth().gapX("5", "0").gapY("0", "0"));
	}
	
	public JPanel getMainPanel() {
		return listPanel;
	}
	
	private JScrollPane getTablePanel() {
		wordListTable = new JTable();
		tableModel.setColumnIdentifiers(new String[] {"Deutsch", "Fremdsprache"});
		wordListTable.setModel(tableModel);
		JScrollPane tablePanel = new JScrollPane(wordListTable);
		
		return tablePanel;
	}
	
//	private JPanel getModifyListPanel() {
//		JPanel modifyListPanel = new JPanel(new MigLayout(new LC().wrap()));
//		JButton addWordButton = new JButton("Hinzufügen");
//		JButton modifyWordButton = new JButton("Bearbeiten");
//		JButton removeWordButton = new JButton("Löschen");
//		
//		modifyListPanel.add(addWordButton, new CC().width(":120:").push().alignX("center"));
//		modifyListPanel.add(modifyWordButton, new CC().width(":120:").alignX("center"));
//		modifyListPanel.add(removeWordButton, new CC().width(":120:").alignX("center"));
//		
//		TitledBorder border = new TitledBorder("Vokabeln bearbeiten");
//		modifyListPanel.setBorder(border);
//		
//		addWordButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				@SuppressWarnings("unused")
//				AddModifyDialog addDialog = new AddModifyDialog(tableModel, -1);
//			}
//		});
//		modifyWordButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				@SuppressWarnings("unused")
//				AddModifyDialog modifyDialog = new AddModifyDialog(tableModel, wordListTable.getSelectedRow());
//			}
//		});
//		
//		removeWordButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				WordListManagement wordListManagement = new WordListManagement(tableModel);
//				wordListManagement.removeWordPair(wordListTable.getSelectedRows());
//			}
//		});
//		
//		return modifyListPanel;
//	}
	
//	private JPanel getSearchPanel() {
//		JPanel searchPanel = new JPanel(new MigLayout(new LC().wrap()));
//		final JTextField searchWordField = new JTextField();
//		JButton searchWordButton = new JButton("Suchen");
//		
//		searchPanel.add(searchWordField, new CC().width(":220:"));
//		searchPanel.add(searchWordButton, new CC().width(":120:").alignX("center"));
//		
//		TitledBorder border = new TitledBorder("Vokabeln suchen");
//		searchPanel.setBorder(border);
//		
//		searchWordButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				int index = 0;
//		
//				WordListManagement wordListManagement = new WordListManagement(tableModel);
//				index = wordListManagement.searchWord(searchWordField.getText());
//				wordListTable.changeSelection(index, 0, false, false);
//			}
//		});
//		
//		return searchPanel;
//	}
	
	private JPanel getConfigurationPanel() {
		JPanel configurationPanel = new JPanel(new MigLayout(new LC().wrap()));
		
		JLabel orderLabel = new JLabel("Abfragereihenfolge:");
		orderComboBox = new JComboBox<String>();
		if(ProductLineConfiguration.inquiryOrderChronological) {
			orderComboBox.addItem("Chronologisch");
		}
		if(ProductLineConfiguration.inquiryOrderSorted) {
			orderComboBox.addItem("Sortiert");
		}
//		orderComboBox.addItem("Zufällig");
		
		JLabel directionLabel = new JLabel("Abfragerichtung");
		directionComboBox = new JComboBox<String>();
		if(ProductLineConfiguration.inquiryDirectionGermanForeign) {
			directionComboBox.addItem("Deutsch » Fremdsprache");
		}
		if(ProductLineConfiguration.inquiryDirectionForeignGerman) {
			directionComboBox.addItem("Fremdsprache » Deutsch");
		}
//		directionComboBox.addItem("Zufällig");
		
		JLabel typeLabel = new JLabel("Abfragetyp");
		typeComboBox = new JComboBox<String>();
		if(ProductLineConfiguration.inquiryTypeKeyboard) {
			typeComboBox.addItem("Tastatureingabe");
		}
//		typeComboBox.addItem("Mehrfachauswahl");
//		typeComboBox.addItem("Quiz");
		
		TitledBorder border = new TitledBorder("Konfiguration");
		configurationPanel.setBorder(border);
		
		configurationPanel.add(orderLabel);
		configurationPanel.add(orderComboBox, new CC().width(":215:"));
		configurationPanel.add(directionLabel);
		configurationPanel.add(directionComboBox, new CC().grow());
		configurationPanel.add(typeLabel);
		configurationPanel.add(typeComboBox, new CC().grow());
		
		return configurationPanel;
	}
	
	private JPanel getStartPracticePanel() {
		JPanel startPracticePanel = new JPanel(new MigLayout());
		
		JButton startPracticeButton = new JButton("Starten");
		
		startPracticePanel.add(startPracticeButton, new CC().push().width(":120:").alignX("center"));
		
		TitledBorder border = new TitledBorder("Übung starten");
		startPracticePanel.setBorder(border);
		
		startPracticeButton.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "unused" })
			public void actionPerformed(ActionEvent e) {
				PracticeFrame practiceFrame = new PracticeFrame(tableModel.getDataVector(), InquiryType.getEnumeration(typeComboBox.getSelectedItem().toString()), InquiryOrder.getEnumeration(orderComboBox.getSelectedItem().toString()), InquiryDirection.getEnumeration(directionComboBox.getSelectedItem().toString()));
			}
		});
		
		return startPracticePanel;
	}
	
	public JTable getWordListTable() {
		return wordListTable;
	}

}
