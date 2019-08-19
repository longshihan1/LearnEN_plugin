package com.longshihan.learnEN2.rightmenu;

import com.google.gson.Gson;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.longshihan.learnEN2.http.HttpResponceListener;
import com.longshihan.learnEN2.http.HttpUtils;
import com.longshihan.learnEN2.http.ResponceInfo;
import com.longshihan.learnEN2.model.EveryDayWordInfo;
import com.longshihan.learnEN2.setting.SettingState;
import com.longshihan.learnEN2.setting.model.SettingConfig;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class LearnToolsWindowFactory implements ToolWindowFactory, RightMenuRefreshListener, HttpResponceListener {
    HttpUtils httpUtils;
    NavigatorPanel navigatorPanel;
    SettingConfig config;
    int startIndex;
    String dictId;
    int pageSize;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        config = SettingState.getInstance().getConfig();
        if (config == null) {
            config = new SettingConfig();
        }
        if (TextUtils.isEmpty(config.getDictId())) {
            config.setDictId("BEC_2");
        }
        if (config.getPagesize() == 0) {
            config.setPagesize(20);
        }
        startIndex=config.getStartIndex();
        dictId=config.getDictId();
        pageSize=config.getPagesize();
        navigatorPanel = new NavigatorPanel(toolWindow, project, this, config);
        Content content = contentFactory.createContent(navigatorPanel, "", false);
        toolWindow.getContentManager().addContent(content);
        httpUtils = new HttpUtils(this);
        getDataUrl();
        config.setStartIndex(config.getStartIndex() + config.getPagesize());
        SettingState.getInstance().setConfig(config);
    }

    public void getDataUrl() {
        int endIndex = startIndex + pageSize;
        httpUtils.doGet("http://reciteword.youdao.com/reciteword/v1/words?bookId=" +dictId + "&wordRanks=%5B" + startIndex + "-" + endIndex + "%5D");
    }

    @Override
    public void onNext(int pageSize, String dictId) {
        config.setPagesize(pageSize);
        this.pageSize=pageSize;
        if (!dictId.equals(config.getDictId())) {
            config.setStartIndex(0);
            startIndex=0;
        }else {
            config.setStartIndex(config.getStartIndex()+config.getPagesize());
            startIndex=startIndex+pageSize;
        }
        config.setDictId(dictId);
        this.dictId=dictId;
        SettingState.getInstance().setConfig(config);
        getDataUrl();
    }

    @Override
    public void onRefresh() {
        getDataUrl();
    }

    @Override
    public void onGetMessage(ResponceInfo info) {
        if (info != null && info.isSuccess() && info.getData() != null) {
            Gson gson = new Gson();
            EveryDayWordInfo info1 = gson.fromJson(info.getData(), EveryDayWordInfo.class);
            if (info1 != null && info1.getWords() != null && info1.getWords().size() > 0) {
                System.out.println("刷新");
                navigatorPanel.putData(info1);
            }
        }
    }
}
