package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import vocabularytrainer.core.InquiryDirection;
import vocabularytrainer.core.InquiryOrder;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class MainPanel {
	
	private JPanel listPanel;
	private JTable wordListTable;
	private WordListTableModel tableModel;
	private JComboBox<String> orderComboBox;
	private JComboBox<String> directionComboBox;
	private JComboBox<String> typeComboBox;
	
	public MainPanel(WordListTableModel tableModel) {
		this.tableModel = tableModel;
		listPanel = new JPanel(new MigLayout());
		
		listPanel.add(getTablePanel(), new CC().dockWest().gapX("0", "5").gapY("5", "5"));
		listPanel.add(getConfigurationPanel(), new CC().dockNorth().gapX("5", "0").gapY("0", "0"));
		listPanel.add(getStartPracticePanel(), new CC().dockNorth().gapX("5", "0").gapY("0", "0"));
	}
	
	public JPanel getMainPanel() {
		return listPanel;
	}
	
	private JScrollPane getTablePanel() {
		wordListTable = new JTable();
		wordListTable.setModel(tableModel);
		JScrollPane tablePanel = new JScrollPane(wordListTable);
		
		return tablePanel;
	}
	
	private JPanel getConfigurationPanel() {
		JPanel configurationPanel = new JPanel(new MigLayout(new LC().wrap()));
		
		JLabel orderLabel = new JLabel("Abfragereihenfolge:");
		orderComboBox = new JComboBox<String>();
		//#ifdef Chronologisch
		orderComboBox.addItem("Chronologisch");
		//#endif
		//#ifdef Sortiert
		orderComboBox.addItem("Sortiert");
		//#endif
		
		JLabel directionLabel = new JLabel("Abfragerichtung");
		directionComboBox = new JComboBox<String>();
		//#ifdef DeutschFremdsprache
		directionComboBox.addItem("Deutsch » Fremdsprache");
		//#endif
		//#ifdef FremdspracheDeutsch
		directionComboBox.addItem("Fremdsprache » Deutsch");
		//#endif
		
		JLabel typeLabel = new JLabel("Abfragetyp");
		typeComboBox = new JComboBox<String>();
		//#ifdef Tastatureingabe
		typeComboBox.addItem("Tastatureingabe");
		//#endif
		
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
			@SuppressWarnings({ "unused" })
			public void actionPerformed(ActionEvent e) {
				if(tableModel.getRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Es sind keine Vokabeln in der Liste hinterlegt.", "Warnung", JOptionPane.WARNING_MESSAGE);
				} else {
					PracticeFrame practiceFrame = new PracticeFrame(tableModel.getData(), InquiryOrder.getEnumeration(orderComboBox.getSelectedItem().toString()), InquiryDirection.getEnumeration(directionComboBox.getSelectedItem().toString()));
				}
			}
		});
		
		return startPracticePanel;
	}
	
	public JTable getWordListTable() {
		return wordListTable;
	}

}
