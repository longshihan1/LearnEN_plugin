package com.longshihan.learnEN2.setting;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class LearnENSettingConfigurable implements SearchableConfigurable {
    private LeanENForm form;
    @NotNull
    @Override
    public String getId() {
        return "com.longshihan.learnEn.settingID";
    }

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName() {
        return "外语学习";
    }

    @Nullable
    @Override
    public JComponent createComponent() {
        form=new LeanENForm();
        form.createUI();
        return form.getContentPane();
    }

    @Override
    public boolean isModified() {
        return form.isModified();
    }

    @Override
    public void apply() throws ConfigurationException {
        form.apply();
    }
}
