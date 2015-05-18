package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class SearchDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private JTable wordListTable;

	public SearchDialog(DefaultTableModel tableModel, JTable wordListTable) {
		super();
		super.setTitle("Vokabel suchen");
		this.tableModel = tableModel;
		this.wordListTable = wordListTable;

		setContentPane(getPanel());
		setResizable(false);
	    setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}
	
	private JPanel getPanel() {
		JPanel panel = new JPanel(new MigLayout(new LC().wrap()));
		JLabel searchLabel = new JLabel("Vokabel eingeben:");
		final JTextField searchWordField = new JTextField();
		JButton searchWordButton = new JButton("OK");
		JButton cancelButton = new JButton("Abbrechen");
		
		panel.add(searchLabel);
		panel.add(searchWordField, new CC().width(":250:"));
		panel.add(searchWordButton, new CC().span().split(2).grow().alignX("center").width("::100"));
		panel.add(cancelButton, new CC().grow().width("::100"));
		
		searchWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = 0;
		
				WordListManagement wordListManagement = new WordListManagement(tableModel);
				index = wordListManagement.searchWord(searchWordField.getText());
				wordListTable.changeSelection(index, 0, false, false);
				setVisible(false);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		return panel;
	}
}
