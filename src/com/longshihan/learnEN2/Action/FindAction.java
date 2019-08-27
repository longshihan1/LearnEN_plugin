package com.longshihan.learnEN2.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.xml.XmlTag;
import com.longshihan.learnEN2.Action.compat.FindUsagesCompat;
import com.longshihan.learnEN2.Action.utils.ResourceUsageCountUtils;

public class FindAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        VirtualFile vFile = e.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (vFile == null) {
            return;
        }
        String fileName = vFile.getName();
        if (!fileName.endsWith(".xml")) {
            return;
        }
        PsiFile pFile = e.getData(PlatformDataKeys.PSI_FILE);
        if (pFile == null) {
            return;
        }
//        for (PsiElement psiElement : pFile.getChildren()) {
//            System.out.println(psiElement.getText() + " is: " + (psiElement instanceof XmlTag));
//            if (psiElement instanceof XmlTag) {
//                System.out.println(FindUsagesCompat.findUsage((XmlTag) psiElement));
//            }
//        }
        pFile.accept(new XmlRecursiveElementVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                super.visitElement(element);
                if (element instanceof XmlTag) {
                    System.out.println(element.getText());
                    if (ResourceUsageCountUtils.isTargetTagToCount(element)) {
                        System.out.println(FindUsagesCompat.findUsage((XmlTag) element));
                    }
                }
            }
        });
    }
}
