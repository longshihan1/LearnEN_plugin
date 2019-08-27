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
    private JComboBox jCheckBoxPromt=new JComboBox();
    private JComboBox jCheckBoxHistory=new JComboBox();
    private JComboBox jCheckNoxRel=new JComboBox();
    public LeanENForm() {}
    public void createUI() {
        mainPanel.setLayout(new GridLayout(10,0));
        JPanel stateMainPane = new JPanel(new FlowLayout(0));
        JPanel statusPane=new JPanel();
        statusPane.add(new JLabel("是否打开近义词显示："));
        jCheckBoxPromt.addItem("TRUE");
        jCheckBoxPromt.addItem("FALSE");
        statusPane.add(jCheckBoxPromt);
        stateMainPane.add(statusPane);
        JPanel historyPane=new JPanel();
        historyPane.add(new JLabel("是否打开例句显示："));
        jCheckBoxHistory.addItem("TRUE");
        jCheckBoxHistory.addItem("FALSE");
        historyPane.add(jCheckBoxHistory);
        stateMainPane.add(historyPane);
        JPanel relPane=new JPanel();
        relPane.add(new JLabel("是否打开同根显示："));
        jCheckNoxRel.addItem("TRUE");
        jCheckNoxRel.addItem("FALSE");
        relPane.add(jCheckNoxRel);
        stateMainPane.add(relPane);
        mainPanel.add(stateMainPane);
        SettingConfig config = SettingState.getInstance().getConfig();
        if (config != null) {
            this.jCheckBoxPromt.setSelectedIndex(config.isSyno()?0:1);
            this.jCheckBoxHistory.setSelectedIndex(config.isSentences()?0:1);
            this.jCheckNoxRel.setSelectedIndex(config.isRelWord()?0:1);
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
        config.setSyno(jCheckBoxHistory.getSelectedIndex()==0);
        config.setSentences(jCheckBoxPromt.getSelectedIndex()==0);
        config.setRelWord(jCheckNoxRel.getSelectedIndex()==0);
        config.setLearnType("EN");
        SettingState.getInstance().setConfig(config);

    }

    public boolean isModified() {
        boolean modified = true;
        return modified;
    }
}
