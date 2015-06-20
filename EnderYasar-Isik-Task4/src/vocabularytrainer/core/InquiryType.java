package vocabularytrainer.core;

public enum InquiryType {
	//#ifdef Tastatureingabe
	KEYBOARD
	//#endif
	;

	public static InquiryType getEnumeration(String type) {
		//#ifdef Tastatureingabe
		if(type.equals("Tastatureingabe")) {
			return KEYBOARD;
		}
		//#endif

		return null;
	}
}
