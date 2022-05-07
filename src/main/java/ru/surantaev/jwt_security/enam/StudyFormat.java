package ru.surantaev.jwt_security.enam;


public enum StudyFormat {

    CORRESPONDENCE_FORM("заочно"),
    FULL_TIME_FORM("очно");

    String format;

    StudyFormat(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    /*.antMatchers( "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()*/
}
