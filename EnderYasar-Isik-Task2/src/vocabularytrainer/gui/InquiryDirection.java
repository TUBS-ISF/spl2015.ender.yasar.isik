package vocabularytrainer.gui;

public enum InquiryDirection {
	GERMAN_TO_FOREIGN, FOREIGN_TO_GERMAN, CHANCE;
		
	public static InquiryDirection getEnumeration(String direction) {
		if(direction.equals("Deutsch » Fremdsprache")) {
			return GERMAN_TO_FOREIGN;
		} else if(direction.equals("Fremdsprache » Deutsch")) {
			return FOREIGN_TO_GERMAN;
		} else if(direction.equals("Zufällig")) {
			return CHANCE;
		} else {
			return null;
		}
	}
}
