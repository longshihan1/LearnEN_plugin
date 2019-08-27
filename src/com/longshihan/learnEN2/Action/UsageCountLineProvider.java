package com.longshihan.learnEN2.Action;

import com.intellij.codeHighlighting.Pass;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.markup.GutterIconRenderer;
import com.intellij.openapi.editor.markup.SeparatorPlacement;
import com.intellij.openapi.util.Computable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlTag;
import com.longshihan.learnEN2.Action.compat.FindUsagesCompat;
import com.longshihan.learnEN2.Action.utils.DataUtils;
import com.longshihan.learnEN2.Action.utils.PropertiesUtils;
import com.longshihan.learnEN2.Action.utils.ResourceUsageCountUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class UsageCountLineProvider implements LineMarkerProvider {

    @Nullable
    @Override
    public LineMarkerInfo getLineMarkerInfo(@NotNull PsiElement psiElement) {
        if (!ResourceUsageCountUtils.isTargetTagToCount(psiElement)) {
            return null;
        }
        if (psiElement instanceof XmlTag) {
            String name = ((XmlTag) psiElement).getLocalName() + ":" + ((XmlTagImpl) psiElement).getAttributes()[0].getValue();
            Integer integer = DataUtils.resourcesMap.get(name);
            if (integer != null) {
                return new MyLineMarkerInfo(psiElement, integer.intValue());
            }
        }
        return null;
    }

    @Override
    public void collectSlowLineMarkers(@NotNull List<PsiElement> list, @NotNull Collection<LineMarkerInfo> collection) {

    }

    private class MyLineMarkerInfo extends LineMarkerInfo<PsiElement> {

        public MyLineMarkerInfo(PsiElement element, int count) {
            super(element, element.getTextRange(), new MyIcon(count), Pass.UPDATE_ALL, null, null, GutterIconRenderer.Alignment.RIGHT);
            separatorPlacement = SeparatorPlacement.BOTTOM;
        }

    }

    public static class MyIcon extends com.intellij.util.ui.EmptyIcon {

        private int count;
        private int length;

      public   MyIcon(int count) {
            super(8, 8);
            this.count = count;
            int temp = count;
            length ++;
            while (temp / 10 != 0) {
                length ++;
                temp /= 10;
            }
        }

        @Override
        public void paintIcon(Component c, Graphics g, int i, int j) {
            if (count == -1) {
                return;
            }
            g.setColor(count <= 0 ? PropertiesUtils.getZeroColor() : count == 1 ? PropertiesUtils.getOneColor() : PropertiesUtils.getOtherColor());
            g.drawString(String.valueOf(count), i, (int)(j + getIconHeight() + 1.5));
        }

        @Override
        public int getIconWidth() {
            return length * 5;
        }

        @Override
        public int getIconHeight() {
            return 8;
        }
    }

    private int findTagUsage(XmlTag element) {
        return FindUsagesCompat.findUsage(element);
    }


}