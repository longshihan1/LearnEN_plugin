package com.longshihan.learnEN2.model;

import java.util.List;

public class EveryDayWordInfo {

    private String reason;
    private int code;
    private List<WordsBeanX> words;
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public List<WordsBeanX> getWords() { return words; }
    public void setWords(List<WordsBeanX> words) {
        this.words = words;
    }
}
