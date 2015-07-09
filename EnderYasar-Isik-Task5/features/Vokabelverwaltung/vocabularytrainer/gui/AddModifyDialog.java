package vocabularytrainer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vocabularytrainer.gui.controller.AddWordPairListener;
import vocabularytrainer.gui.controller.ModifyWordPairListener;
import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class AddModifyDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private final String addWordPairTitle = "Vokabel hinzufügen";
	private final String modifyWordPairTitle = "Vokabel hinzufügen";
	private WordListTableModel tableModel;
	private int index;

	// Wenn ein Vokabelpaar hinzugefügt werden soll, dann wird index = -1 
	// gesetzt. Sonst der reguläre Zeilenindex, in der das Vokabelpaar 
	// angepasst werden soll.
	public AddModifyDialog(WordListTableModel tableModel, int index) {
		super();
		this.tableModel = tableModel;
		this.index = index;
		
		if(index == -1) {
			setTitle(addWordPairTitle);
		} else {
			setTitle(modifyWordPairTitle);
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
		JTextField germanTextField = new JTextField();
		JLabel foreignLabel = new JLabel("Fremdsprache:");
		JTextField foreignTextField = new JTextField();
		
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
		
		if(index > -1) {
			okButton.addActionListener(new ModifyWordPairListener(tableModel, index, germanTextField, foreignTextField));
		} else {
			okButton.addActionListener(new AddWordPairListener(tableModel, germanTextField, foreignTextField));
		}
		
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		
		return panel;
	}
	
}
