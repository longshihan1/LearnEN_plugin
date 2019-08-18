package com.longshihan.learnEN2.rightmenu;


import com.longshihan.learnEN2.model.WordsBeanX;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class WordElement extends AbstractListModel {

    private List<WordsBeanX> list= new ArrayList<>();
    public WordElement() {
        this.list = new ArrayList<>();
    }
    public WordElement(List<WordsBeanX> list) {
        this.list = list;
    }

    public WordElement UpdateData(List<WordsBeanX> list){
        this.list=list;
        return this;
    }
    @Override
    public int getSize() {
        return list.size();
    }

    @Override
    public Object getElementAt(int index) {
        return list.get(index);
    }
}
