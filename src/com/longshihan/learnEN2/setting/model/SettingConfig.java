package com.longshihan.learnEN2.setting.model;

public class SettingConfig {
    private String filePath;
    private String learnType;
    private boolean startMsg;
    private boolean addHistory;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLearnType() {
        return learnType;
    }

    public void setLearnType(String learnType) {
        this.learnType = learnType;
    }

    public boolean isStartMsg() {
        return startMsg;
    }

    public void setStartMsg(boolean startMsg) {
        this.startMsg = startMsg;
    }

    public boolean isAddHistory() {
        return addHistory;
    }

    public void setAddHistory(boolean addHistory) {
        this.addHistory = addHistory;
    }
}
