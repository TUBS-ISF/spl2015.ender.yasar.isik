package vocabularytrainer.core;

public enum InquiryOrder {
	//#ifdef Chronologisch
//@	CHRONOLOGICAL,
	//#endif
	//#ifdef Sortiert
	SORTED
	//#endif
	;

	public static InquiryOrder getEnumeration(String order) {
		//#ifdef Chronologisch
//@		if(order.equals("Chronologisch")) {
//@			return CHRONOLOGICAL;
//@		}
		//#endif
		//#ifdef Sortiert
		if(order.equals("Sortiert")) {
			return SORTED;
		}
		//#endif

		return null;
	}
}
