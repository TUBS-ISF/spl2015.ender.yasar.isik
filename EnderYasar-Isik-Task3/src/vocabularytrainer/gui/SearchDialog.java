//#ifdef Suchen
//@package vocabularytrainer.gui;
//@
//@import java.awt.event.ActionEvent;
//@import java.awt.event.ActionListener;
//@
//@import javax.swing.JButton;
//@import javax.swing.JDialog;
//@import javax.swing.JLabel;
//@import javax.swing.JPanel;
//@import javax.swing.JTable;
//@import javax.swing.JTextField;
//@
//@import vocabularytrainer.gui.controller.SearchWordPairListener;
//@import net.miginfocom.layout.CC;
//@import net.miginfocom.layout.LC;
//@import net.miginfocom.swing.MigLayout;
//@
//@public class SearchDialog extends JDialog {
//@	
//@	private static final long serialVersionUID = 1L;
//@	private WordListTableModel tableModel;
//@	private JTable wordListTable;
//@
//@	public SearchDialog(WordListTableModel tableModel, JTable wordListTable) {
//@		super();
//@		super.setTitle("Vokabel suchen");
//@		this.tableModel = tableModel;
//@		this.wordListTable = wordListTable;
//@
//@		setContentPane(getPanel());
//@		setResizable(false);
//@	    setLocationRelativeTo(null);
//@		setVisible(true);
//@		pack();
//@	}
//@	
//@	private JPanel getPanel() {
//@		JPanel panel = new JPanel(new MigLayout(new LC().wrap()));
//@		JLabel searchLabel = new JLabel("Vokabel eingeben:");
//@		final JTextField searchWordTextField = new JTextField();
//@		JButton searchWordButton = new JButton("OK");
//@		JButton cancelButton = new JButton("Abbrechen");
//@		
//@		panel.add(searchLabel);
//@		panel.add(searchWordTextField, new CC().width(":250:"));
//@		panel.add(searchWordButton, new CC().span().split(2).grow().alignX("center").width("::100"));
//@		panel.add(cancelButton, new CC().grow().width("::100"));
//@		
//@		searchWordButton.addActionListener(new SearchWordPairListener(wordListTable, tableModel, searchWordTextField));
//@		
//@		cancelButton.addActionListener(new ActionListener() {
//@			public void actionPerformed(ActionEvent e) {
//@				dispose();
//@			}
//@		});
//@		
//@		return panel;
//@	}
//@}
//#endif
