package com.longshihan.learnEN2.Action;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlTag;
import com.longshihan.learnEN2.Action.compat.FindUsagesCompat;
import com.longshihan.learnEN2.Action.utils.DataUtils;
import com.longshihan.learnEN2.Action.utils.ResourceUsageCountUtils;
import org.jetbrains.annotations.NotNull;

public class UsageCountAnnotatar implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (!ResourceUsageCountUtils.isTargetTagToCount(element)) {
            return ;
        }
//        if (element instanceof XmlTag) {
//            int count = findTagUsage((XmlTag) element);
//            TextRange range = new TextRange(element.getTextRange().getStartOffset(),
//                    element.getTextRange().getEndOffset());
//            holder.createInfoAnnotation(range, count+"");
//        }
        if (element instanceof XmlTag&&((XmlTagImpl) element).getAttributes().length>0) {
            String name = ((XmlTag) element).getLocalName() + ":" + ((XmlTagImpl) element).getAttributes()[0].getValue();
            Integer integer = DataUtils.resourcesMap.get(name);
            if (integer != null) {
                TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset());
                holder.createInfoAnnotation(range, integer.intValue()+"");
            }
        }
    }
}
