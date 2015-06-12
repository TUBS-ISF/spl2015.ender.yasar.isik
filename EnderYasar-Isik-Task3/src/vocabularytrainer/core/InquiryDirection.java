package vocabularytrainer.core;

public enum InquiryDirection {
	//#ifdef DeutschFremdsprache
	GERMAN_TO_FOREIGN,
	//#endif
	//#ifdef FremdspracheDeutsch
	FOREIGN_TO_GERMAN
	//#endif
	;
		
	public static InquiryDirection getEnumeration(String direction) {
		//#ifdef DeutschFremdsprache
		if(direction.equals("Deutsch » Fremdsprache")) {
			return GERMAN_TO_FOREIGN;
		}
		//#endif
		//#ifdef FremdspracheDeutsch
		if(direction.equals("Fremdsprache » Deutsch")) {
			return FOREIGN_TO_GERMAN;
		}
		//#endif
		return null;
	}
}
