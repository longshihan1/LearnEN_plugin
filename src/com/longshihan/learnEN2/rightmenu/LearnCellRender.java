package com.longshihan.learnEN2.rightmenu;

import com.intellij.util.containers.hash.HashMap;
import com.longshihan.learnEN2.model.WordsBeanX;
import com.longshihan.learnEN2.setting.model.SettingConfig;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class LearnCellRender implements ListCellRenderer {
    private SettingConfig config;

    public LearnCellRender(SettingConfig config) {
        this.config = config;
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (value != null) {
            if (value instanceof WordsBeanX) {
                WordsBeanX data = (WordsBeanX) value;
//                JPanel jPanel = new JPanel(new GridLayout(0, 1));
                Box jPanel = Box.createVerticalBox();    //创建横向Box容器
//                jPanel.setBackground(Color.WHITE);
                if (data.getWord() != null) {
                    JLabel titleLabel = new JLabel(data.getWord().getWordHead());
                    titleLabel.setFont(new Font(null, Font.ITALIC, 20));
                    jPanel.add(titleLabel);
                    if (data.getWord().getContent() != null) {
                        jPanel.add(new JLabel("美：[" + data.getWord().getContent().getUsphone() + "]"));
                        jPanel.add(new JLabel("英：[" + data.getWord().getContent().getUsphone() + "]"));
                        Map<String, JTextArea> adjMap = new HashMap<>();
                        if (data.getWord().getContent().getSyno() != null &&
                                data.getWord().getContent().getSyno().getSynos() != null) {
                            List<WordsBeanX.WordBean.ContentBean.SynoBean.SynosBean> adjList
                                    = data.getWord().getContent().getSyno().getSynos();
                            for (int i = 0; i < adjList.size(); i++) {
                                JTextArea adjTextArea = new JTextArea();
                                adjTextArea.setLineWrap(true);
                                String adjStr = adjList.get(i).getPos() + ":" + adjList.get(i).getTran();
                                adjTextArea.append(adjStr);
                                if (config!=null&&config.isSyno()) {
                                    if (adjList.get(i).getHwds() != null) {
                                        adjTextArea.append("\n  ");
                                        adjTextArea.append("近义词：");
                                        for (int j = 0; j < adjList.get(i).getHwds().size(); j++) {
                                            if (!TextUtils.isEmpty(adjList.get(i).getHwds().get(j).getW())) {
                                                adjTextArea.append(adjList.get(i).getHwds().get(j).getW() + ",");
                                            }
                                        }
                                    }
                                }
                                adjMap.put(adjList.get(i).getPos(), adjTextArea);
                            }
                        }
                        if (data.getWord().getContent().getTrans() != null) {
                            List<WordsBeanX.WordBean.ContentBean.TransBean> adjSample
                                    = data.getWord().getContent().getTrans();
                            for (int i = 0; i < adjSample.size(); i++) {
                                if (adjSample.get(i) != null
                                        && adjMap.containsKey(adjSample.get(i).getPos())) {
                                    JTextArea adjTextArea = adjMap.get(adjSample.get(i).getPos());
                                    if (!TextUtils.isEmpty(adjSample.get(i).getDescOther())
                                            && !TextUtils.isEmpty(adjSample.get(i).getTranOther())) {
                                        adjTextArea.append("\n  " + adjSample.get(i).getDescOther() + ":" + adjSample.get(i).getTranOther());
                                    }
                                    if (!TextUtils.isEmpty(adjSample.get(i).getDescCn())
                                            && !TextUtils.isEmpty(adjSample.get(i).getTranCn())) {
                                        adjTextArea.append("\n  " + adjSample.get(i).getDescCn() + ":" + adjSample.get(i).getTranCn());
                                    }
                                }

                            }
                        }
                        if (config!=null&&config.isRelWord()) {
                            if (data.getWord().getContent().getRelWord() != null && data.getWord().getContent().getRelWord().getRels() != null) {
                                List<WordsBeanX.WordBean.ContentBean.RelWordBean.RelsBean> adjSample
                                        = data.getWord().getContent().getRelWord().getRels();
                                for (int i = 0; i < adjSample.size(); i++) {
                                    if (adjSample.get(i) != null
                                            && adjMap.containsKey(adjSample.get(i).getPos())) {
                                        JTextArea adjTextArea = adjMap.get(adjSample.get(i).getPos());
                                        adjTextArea.append("\n  " + "同根词" + ":");
                                        for (int j = 0; j < adjSample.get(i).getWords().size(); j++) {
                                            if (adjSample.get(i).getWords().get(j) != null) {
                                                adjTextArea.append(adjSample.get(i).getWords().get(j).getHwd() +
                                                        ":" + adjSample.get(i).getWords().get(j).getTran() + ",");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        for (JTextArea jTextArea : adjMap.values()) {
                            jPanel.add(jTextArea);
                        }
                        if (config!=null&&config.isSentences()) {
                            if (data.getWord().getContent().getSentence() != null
                                    && data.getWord().getContent().getSentence().getSentences() != null) {
                                List<WordsBeanX.WordBean.ContentBean.SentenceBean.SentencesBean> adjSample
                                        = data.getWord().getContent().getSentence().getSentences();
                                JLabel label = new JLabel("例句");
                                label.setFont(new Font(null, Font.PLAIN, 18));
                                jPanel.add(label);
                                for (int i = 0; i < adjSample.size(); i++) {
                                    jPanel.add(new JLabel(  adjSample.get(i).getSContent()));
                                    jPanel.add(new JLabel(  adjSample.get(i).getSCn()));
                                }
                            }
                        }

                        if (data.getWord().getContent().getPhrase() != null
                                && data.getWord().getContent().getPhrase().getPhrases() != null) {
                            List<WordsBeanX.WordBean.ContentBean.PhraseBean.PhrasesBean> adjSample
                                    = data.getWord().getContent().getPhrase().getPhrases();
                            JLabel label = new JLabel("短语");
                            label.setFont(new Font(null, Font.PLAIN, 18));
                            jPanel.add(label);
                            for (int i = 0; i < adjSample.size(); i++) {
                                jPanel.add(new JLabel(  adjSample.get(i).getPContent()));
                                jPanel.add(new JLabel(  adjSample.get(i).getPCn()));
                            }
                        }
                        if (data.getWord().getContent().getRemMethod() != null) {
                            JLabel label = new JLabel(data.getWord().getContent().getRemMethod().getDesc());
                            label.setFont(new Font(null, Font.PLAIN, 18));
                            jPanel.add(label);
                            jPanel.add(new JLabel(data.getWord().getContent().getRemMethod().getVal()));

                        }
                    }
                }
                jPanel.add(new JLabel("     "));
                return jPanel;
            } else {
                return new JLabel("对象解析失败");
            }
        } else {
            return new JLabel("对象解析失败");
        }
    }
}
