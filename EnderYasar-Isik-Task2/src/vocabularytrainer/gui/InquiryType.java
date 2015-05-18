package vocabularytrainer.gui;

public enum InquiryType {
	KEYBOARD, QUIZ, MULTIPLE_CHOICE;
	
	public static InquiryType getEnumeration(String type) {
		if(type.equals("Tastatureingabe")) {
			return KEYBOARD;
		} else if(type.equals("Quiz")) {
			return QUIZ;
		} else if(type.equals("Mehrfachauswahl")) {
			return MULTIPLE_CHOICE;
		} else {
			return null;
		}
	}
}
