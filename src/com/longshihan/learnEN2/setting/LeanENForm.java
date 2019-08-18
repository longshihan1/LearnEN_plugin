package com.longshihan.learnEN2.setting;

import com.intellij.ide.IdeBundle;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBPanel;
import com.longshihan.learnEN2.setting.model.SettingConfig;
import org.apache.commons.lang.StringUtils;

import javax.swing.*;
import java.awt.*;

public class LeanENForm {

    private JLabel textJlabel;
    public JPanel mainPanel = new JBPanel();
    private TextFieldWithBrowseButton fileFolderBtn = new TextFieldWithBrowseButton();
    private JComboBox jCheckBoxPromt=new JComboBox();
    private JComboBox jCheckBoxHistory=new JComboBox();

    public LeanENForm() {

    }


    public void createUI() {
        mainPanel.setLayout(new GridLayout(10,0));
//        JPanel ttitlePane = new JPanel(new FlowLayout(0));
//        textJlabel=new JLabel("程序员外语学习配置项");
//        ttitlePane.add(textJlabel);
//        mainPanel.add(ttitlePane);
        JPanel stateMainPane = new JPanel(new FlowLayout(0));
        JPanel statusPane=new JPanel();
        statusPane.add(new JLabel("是否开启每日提醒："));
        jCheckBoxPromt.addItem("TRUE");
        jCheckBoxPromt.addItem("FALSE");
        statusPane.add(jCheckBoxPromt);
        stateMainPane.add(statusPane);

        JPanel historyPane=new JPanel();
        historyPane.add(new JLabel("是否开启收藏："));
        jCheckBoxHistory.addItem("TRUE");
        jCheckBoxHistory.addItem("FALSE");
        historyPane.add(jCheckBoxHistory);
        stateMainPane.add(historyPane);

        mainPanel.add(stateMainPane);

        JPanel pathMainPane = new JPanel(new FlowLayout(0));
        JPanel pathPane=new JPanel();
        pathPane.add(new JLabel("字典路径"));
        final FileChooserDescriptor fileChooserDescriptor =
                new FileChooserDescriptor(true, false, false, false, false, false);
        fileFolderBtn.addBrowseFolderListener(
                IdeBundle.message("title.browse.icon"),
                null,
                null, fileChooserDescriptor);

        pathPane.add(fileFolderBtn);
        pathMainPane.add(pathPane);
        mainPanel.add(pathMainPane);
        SettingConfig config = SettingState.getInstance().getConfig();
        if (config != null) {
            this.jCheckBoxPromt.setSelectedIndex(config.isStartMsg()?0:1);
            this.jCheckBoxHistory.setSelectedIndex(config.isAddHistory()?0:1);
            if (StringUtils.isNotBlank(config.getFilePath())) {
                this.fileFolderBtn.setText(config.getFilePath());
            }
        }

    }

    public JPanel getContentPane() {
        return mainPanel;
    }

    public void apply() {
        SettingConfig config=SettingState.getInstance().getConfig();
        if (config==null){
            config=new SettingConfig();
        }
        config.setAddHistory(jCheckBoxHistory.getSelectedIndex()==0);
        config.setStartMsg(jCheckBoxPromt.getSelectedIndex()==0);
        config.setFilePath(fileFolderBtn.getText());
        config.setLearnType("EN");
        SettingState.getInstance().setConfig(config);

    }

    public boolean isModified() {
        boolean modified = true;
        return modified;
    }
}
