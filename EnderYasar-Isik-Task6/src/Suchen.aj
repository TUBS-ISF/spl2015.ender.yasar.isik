import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import vocabularytrainer.core.WordPair;
import vocabularytrainer.gui.MenuBar;
import vocabularytrainer.gui.ToolBar;
import vocabularytrainer.gui.WordListTableModel;

import net.miginfocom.layout.CC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public aspect Suchen {
	
	after(ToolBar toolBar) : call(private void vocabularytrainer.gui.ToolBar.setToolBar()) && this(toolBar) {
		JButton searchWordButton = new JButton(new ImageIcon(getClass().getResource("/search.png")));
		searchWordButton.setBorderPainted(false);
		toolBar.getToolBar().add(searchWordButton);
		searchWordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(toolBar.getTableModel(), toolBar.getWordListTable());
			}
		});
	}
	
	after(MenuBar menuBar) : call(private void vocabularytrainer.gui.MenuBar.setEditMenu()) && this(menuBar) {
		JMenuItem searchWordItem = new JMenuItem("Vokabel suchen");
		menuBar.getEditMenu().addSeparator();
		menuBar.getEditMenu().add(searchWordItem);
		searchWordItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				SearchDialog searchDialog = new SearchDialog(menuBar.getTableModel(), menuBar.getWordListTable());
			}
		});
	}
	
	class SearchDialog extends JDialog {
		
		private static final long serialVersionUID = 1L;
		private WordListTableModel tableModel;
		private JTable wordListTable;

		public SearchDialog(WordListTableModel tableModel, JTable wordListTable) {
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
			final JTextField searchWordTextField = new JTextField();
			JButton searchWordButton = new JButton("OK");
			JButton cancelButton = new JButton("Abbrechen");
			
			panel.add(searchLabel);
			panel.add(searchWordTextField, new CC().width(":250:"));
			panel.add(searchWordButton, new CC().span().split(2).grow().alignX("center").width("::100"));
			panel.add(cancelButton, new CC().grow().width("::100"));
			
			searchWordButton.addActionListener(new SearchWordPairListener(wordListTable, tableModel, searchWordTextField));
			
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			
			return panel;
		}
	}
	
	public class SearchWordPairListener implements ActionListener {
		
		private JTable wordListTable;
		private WordListTableModel tableModel;
		private JTextField searchWordTextField;
		
		public SearchWordPairListener(JTable wordListTable, WordListTableModel tableModel, JTextField searchWordTextField) {
			this.wordListTable = wordListTable;
			this.tableModel = tableModel;
			this.searchWordTextField = searchWordTextField;
		}

		public void actionPerformed(ActionEvent e) {
			int index = 0;
			for(WordPair wordPair : tableModel.getData().getWordList()) {
				if(wordPair.getGermanWord().equals(searchWordTextField.getText()) | wordPair.getForeignWord().equals(searchWordTextField.getText())) {
					break;
				} else {
					index++;
				}
			}
			wordListTable.changeSelection(index, 0, false, false);
			
			Component component = (Component) e.getSource();
			JDialog dialog = (JDialog) SwingUtilities.getRoot(component);
			dialog.dispose();
		}
	}
}
