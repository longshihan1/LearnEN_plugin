package com.longshihan.learnEN2.rightmenu;

import com.google.gson.Gson;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.longshihan.learnEN2.http.HttpResponceListener;
import com.longshihan.learnEN2.http.HttpUtils;
import com.longshihan.learnEN2.http.ResponceInfo;
import com.longshihan.learnEN2.model.EveryDayWordInfo;
import org.jetbrains.annotations.NotNull;

public class LearnToolsWindowFactory implements ToolWindowFactory,RightMenuRefreshListener,HttpResponceListener{
    HttpUtils httpUtils;
    NavigatorPanel navigatorPanel;
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        navigatorPanel=new NavigatorPanel(toolWindow, project,this);
        Content content = contentFactory.createContent(navigatorPanel, "", false);
        toolWindow.getContentManager().addContent(content);
        httpUtils=new HttpUtils(this);
        httpUtils.doGet("http://reciteword.youdao.com/reciteword/v1/words?bookId=BEC_2&wordRanks=%5B1-20%5D");
    }

    @Override
    public void onRefresh(int pageSize, String dictId, int startIndex) {
        httpUtils.doGet("http://reciteword.youdao.com/reciteword/v1/words?bookId="+dictId+"&wordRanks=%5B"+startIndex+"-"+pageSize+"%5D");
    }

    @Override
    public void onGetMessage(ResponceInfo info) {
        if (info!=null&&info.isSuccess()&&info.getData()!=null){
            Gson gson=new Gson();
            EveryDayWordInfo info1=gson.fromJson(info.getData(),EveryDayWordInfo.class);
            if (info1!=null&&info1.getWords()!=null&&info1.getWords().size()>0){
                navigatorPanel.putData(info1);
            }
        }
    }
}
