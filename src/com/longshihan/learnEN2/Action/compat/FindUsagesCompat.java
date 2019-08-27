package com.longshihan.learnEN2.Action.compat;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.WriteAction;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.xml.XmlTag;

/**
 * Created by qiu on 12/12/17.
 */
public class FindUsagesCompat {

    public static final int ERROR = -1;

    public static int findUsage(XmlTag element) {
//        if (ApplicationInfo.getInstance().getBuild().getBaselineVersion() < 171) {
//            return FindUsagesImpl171.getInstance().findUsage(element);
//        }
        return FindUsagesImpl171.getInstance().findUsage(element);
//        return 1;
    }
    public static int findUsage(String fileName) {
//        if (ApplicationInfo.getInstance().getBuild().getBaselineVersion() < 171) {
//            return FindUsagesImpl171.getInstance().findUsage(element);
//        }
        PsiNamedElement psiNamedElement=PsiNamedElement.EMPTY_ARRAY[0];
        psiNamedElement.setName(fileName);
        return FindUsagesImpl.getInstance().findUsage(psiNamedElement);
//        return 1;
    }
}
