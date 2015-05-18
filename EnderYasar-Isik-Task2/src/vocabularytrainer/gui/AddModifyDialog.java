package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class AddModifyDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private DefaultTableModel tableModel;
	private int index;

	// Wenn ein Vokabelpaar hinzugefügt werden soll, dann wird index = -1 
	// gesetzt. Sonst der reguläre Zeilenindex, in der das Vokabelpaar 
	// angepasst werden soll.
	public AddModifyDialog(DefaultTableModel tableModel, int index) {
		super();
		this.tableModel = tableModel;
		this.index = index;
		
		if(index == -1) {
			setTitle("Vokabel hinzufügen");
		} else {
			setTitle("Vokabel bearbeiten");
		}
		
		setContentPane(getPanel());
		setResizable(false);
	    setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}
	
	private JPanel getPanel() {
		JPanel panel = new JPanel(new MigLayout(new LC().wrapAfter(2)));
		JLabel germanLabel = new JLabel("Deutsch:");
		final JTextField germanTextField = new JTextField();
		JLabel foreignLabel = new JLabel("Fremdsprache:");
		final JTextField foreignTextField = new JTextField();
		if(index > -1) {
			germanTextField.setText(tableModel.getValueAt(index, 0).toString());
			foreignTextField.setText(tableModel.getValueAt(index, 1).toString());
		}
		JButton okButton = new JButton("OK");
		JButton cancelButton = new JButton("Abbrechen");
		
		panel.add(germanLabel);
		panel.add(germanTextField, new CC().width(":210:"));
		panel.add(foreignLabel);
		panel.add(foreignTextField, new CC().width(":210:"));
		panel.add(okButton, new CC().span().split(2).grow().alignX("center").width("::100"));
		panel.add(cancelButton, new CC().grow().width("::100"));
		
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WordListManagement wordListManagement = new WordListManagement(tableModel);
				if(index < 0) {
					wordListManagement.addWordPair(germanTextField.getText(), foreignTextField.getText());
				} else {
					wordListManagement.modifyWordPair(index, germanTextField.getText(), foreignTextField.getText());
				}
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
