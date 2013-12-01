package net.hogedriven.backpaper0.javaeeadventcalendar2013;

public class UserId implements WithValidation {

    private final String value;

    public UserId(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String validate() {

        if (value.length() > 10) {
            return "10文字以下でｵﾅｼｬｽ";
        }

        for (char c : value.toCharArray()) {
            if (('a' <= c && c <= 'z') == false
                    && ('A' <= c && c <= 'Z') == false
                    && ('0' <= c && c <= '9') == false) {
                return "アルファベットと数字でよろろ";
            }
        }

        return null;
    }

    public static UserId fromString(String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        return new UserId(value);
    }
}
