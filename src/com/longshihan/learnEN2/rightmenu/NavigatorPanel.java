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
import com.longshihan.learnEN2.setting.model.SettingConfig;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.geom.Dimension2D;
import java.util.HashMap;
import java.util.Map;

public class NavigatorPanel extends SimpleToolWindowPanel implements DataProvider {
    private JPanel queryPanel;
    private JBScrollPane contentScrollPanel;
    private SimpleTree tree;
    private SimpleToolWindowPanel treePanel;
    private WordElement wordElement=new WordElement();
    private  JList list;
    private JComboBox dictcomboBox=new JComboBox();
    private JComboBox pageSizeomboBox=new JComboBox();
    private Map<String,String> dictMap=new HashMap<>();

    public NavigatorPanel(ToolWindow toolWindow, Project project, RightMenuRefreshListener listener, SettingConfig config) {
        super(true, true);
        dictMap.put("BEC_2","商务英语词汇");
        dictMap.put("CET4luan_1","四级词汇");
        ActionManager actionManager = ActionManager.getInstance();
        treePanel=new SimpleToolWindowPanel(true,true);
        JPanel toolsPanel=new JPanel();
        toolsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        for (String dictStr :dictMap.values()) {
            dictcomboBox.addItem(dictStr);
        }
        if ("BEC_2".equals(config.getDictId())){
            dictcomboBox.setSelectedIndex(0);
        }else if ("CET4luan_1".equals(config.getDictId())){
            dictcomboBox.setSelectedIndex(1);
        }
        toolsPanel.add(dictcomboBox);
        pageSizeomboBox.addItem(20);
        pageSizeomboBox.addItem(40);
        pageSizeomboBox.addItem(60);
        if (config.getPagesize()==20){
            pageSizeomboBox.setSelectedIndex(0);
        }else if (config.getPagesize()==40){
            pageSizeomboBox.setSelectedIndex(1);
        }else if (config.getPagesize()==60){
            pageSizeomboBox.setSelectedIndex(2);
        }
        toolsPanel.add(pageSizeomboBox);
        JButton refreshButton=new JButton();
        refreshButton.setText("刷新");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listener!=null){
                    listener.onRefresh(pageSizeomboBox.getSelectedIndex()*20+20, (String) dictMap.keySet().toArray()[dictcomboBox.getSelectedIndex()]);
                }
            }
        });
        toolsPanel.add(refreshButton);
        treePanel.setToolbar(toolsPanel);

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
        list.setModel(new WordElement(info1.getWords()));
    }
}
