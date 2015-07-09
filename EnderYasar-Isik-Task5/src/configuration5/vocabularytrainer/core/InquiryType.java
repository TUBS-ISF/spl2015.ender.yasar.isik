package vocabularytrainer.core; 

public enum  InquiryType {
	KEYBOARD; 

	public static InquiryType getEnumeration(String type) {
		if(type.equals("Tastatureingabe")) {
			return KEYBOARD;
		}

		return null;
	}}
