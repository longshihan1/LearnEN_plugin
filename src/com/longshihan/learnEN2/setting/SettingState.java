package com.longshihan.learnEN2.setting;

import com.intellij.ide.passwordSafe.PasswordSafe;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.util.xmlb.XmlSerializerUtil;
import com.longshihan.learnEN2.setting.model.SettingConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@State(name = "SettingConfig",storages = {@com.intellij.openapi.components.Storage(value = "learnEn-config.xml",
        roamingType = com.intellij.openapi.components.RoamingType.DISABLED)})
public class SettingState implements PersistentStateComponent<SettingState> {
    private SettingConfig initConfig;
    public SettingState() {

    }

    @Nullable
    public static SettingState getInstance() {
        return (SettingState) ServiceManager.getService(SettingState.class);
    }

    @Nullable
    @Override
    public SettingState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull SettingState settingConfig) {
        if (settingConfig == null) {
            return;
        }
        XmlSerializerUtil.copyBean(settingConfig, this);
    }


    public SettingConfig getConfig() {
        if (initConfig==null){
            initConfig=new SettingConfig();
        }
        return initConfig;
    }


    public void setConfig(SettingConfig config) {
        initConfig=config;
    }


    public void savePath(String path) {
        PasswordSafe.getInstance().storePassword(null, getClass(), "learnDictPath", path != null ? path : "");
    }
    public String getPATH(String path) {
        return PasswordSafe.getInstance().getPassword(null, getClass(), "learnDictPath");
    }
}
