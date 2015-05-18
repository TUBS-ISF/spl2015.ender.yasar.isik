package vocabularytrainer.gui;

public enum InquiryOrder {
	CHRONOLOGICAL, SORTED, CHANCE;

	public static InquiryOrder getEnumeration(String order) {
		if(order.equals("Chronologisch")) {
			return CHRONOLOGICAL;
		} else if(order.equals("Sortiert")) {
			return SORTED;
		} else if(order.equals("Zufällig")) {
			return CHANCE;
		} else {
			return null;
		}
	}
}
