package com.longshihan.learnEN2.rightmenu;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DataProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBList;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTextArea;
import com.intellij.ui.components.JBTextField;
import com.intellij.ui.treeStructure.SimpleTree;
import com.longshihan.learnEN2.model.EveryDayWordInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Dimension2D;

public class NavigatorPanel extends SimpleToolWindowPanel implements DataProvider {
    private JPanel queryPanel;
    private JBScrollPane contentScrollPanel;
    private SimpleTree tree;
    private SimpleToolWindowPanel treePanel;
    private WordElement wordElement=new WordElement();
    private  JList list;

    public NavigatorPanel(ToolWindow toolWindow, Project project,RightMenuRefreshListener listener) {
        super(true, true);
        ActionManager actionManager = ActionManager.getInstance();
        treePanel=new SimpleToolWindowPanel(true,true);
        JPanel currentPanel=new JPanel(new FlowLayout());
        JPanel toolsPanel=new JPanel();
        toolsPanel.setLayout(new FlowLayout());
        JButton refreshButton=new JButton();
        refreshButton.setText("刷新");
        toolsPanel.add(refreshButton);
        currentPanel.add(toolsPanel);
        treePanel.setToolbar(currentPanel);

        JScrollPane scrollPane=new JScrollPane();    //创建滚动面板
        treePanel   .add(scrollPane,BorderLayout.CENTER);    //将面板增加到边界布局中央
        list=new JBList();
        //限制只能选择一个元素
        list.setCellRenderer(new LearnCellRender());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(list);    //在滚动面板中显示列表
        list.setModel(wordElement);
        setContent(treePanel);

    }

    public void putData(EveryDayWordInfo info1) {
        list.setModel(wordElement.UpdateData(info1.getWords()));
    }
}
