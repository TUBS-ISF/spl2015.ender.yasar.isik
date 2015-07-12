package vocabularytrainer.core;

public enum InquiryDirection {
	GERMAN_TO_FOREIGN,
	FOREIGN_TO_GERMAN;
		
	public static InquiryDirection getEnumeration(String direction) {
		if(direction.equals("Deutsch » Fremdsprache")) {
			return GERMAN_TO_FOREIGN;
		}
		if(direction.equals("Fremdsprache » Deutsch")) {
			return FOREIGN_TO_GERMAN;
		}
		return null;
	}
}
