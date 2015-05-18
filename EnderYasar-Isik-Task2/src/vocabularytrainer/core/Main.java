package vocabularytrainer.core;

import vocabularytrainer.gui.MainFrame;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

public class Main {

	public static void main(String[] args) {
		for (String arg : args) {
			if(arg.equals("-s")) {
				ProductLineConfiguration.statistics = true;
			} else if(arg.equals("-ix")) {
				ProductLineConfiguration.importXML = true;
			} else if(arg.equals("-ex")) {
				ProductLineConfiguration.exportXML = true;
			} else if(arg.equals("-iosor")) {
				ProductLineConfiguration.inquiryOrderSorted = true;
			} else if(arg.equals("-iochr")) {
				ProductLineConfiguration.inquiryOrderChronological = true;
			} else if(arg.equals("-itk")) {
				ProductLineConfiguration.inquiryTypeKeyboard = true;
			} else if(arg.equals("-idgf")) {
				ProductLineConfiguration.inquiryDirectionGermanForeign = true;
			} else if(arg.equals("-idfg")) {
				ProductLineConfiguration.inquiryDirectionForeignGerman = true;
			} else if(arg.equals("-help")) {
				System.out.println("-s, statistics");
				System.out.println("-ix, importXML");
				System.out.println("-ex, exportXML");
				System.out.println("-iosor, inquiryOrderSorted");
				System.out.println("-iochr, inquiryOrderChronological");
				System.out.println("-itk, inquiryTypeKeyboard");
				System.out.println("-idgf, inquiryDirectionGermanForeign");
				System.out.println("-idfg, inquiryDirectionForeignGerman");
				System.exit(0);
			}
		}
		
		if((!ProductLineConfiguration.importXML && ProductLineConfiguration.exportXML) | (ProductLineConfiguration.importXML && !ProductLineConfiguration.exportXML)) {
			System.out.println("Wenn importXML ausgewaehlt wurde, dann muss auch exportXML ausgewaehlt werden, und umgekehrt");
			System.exit(-1);
		}
		
		MainFrame mainFrame = new MainFrame();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		mainFrame.pack();
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}

}
