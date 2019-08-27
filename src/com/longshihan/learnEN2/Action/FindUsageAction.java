package com.longshihan.learnEN2.Action;

import com.intellij.ide.UiActivity;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.progress.util.ProgressBar;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.XmlRecursiveElementVisitor;
import com.intellij.psi.impl.source.xml.XmlTagImpl;
import com.intellij.psi.xml.XmlTag;
import com.longshihan.learnEN2.Action.compat.FindUsagesCompat;
import com.longshihan.learnEN2.Action.utils.DataUtils;
import com.longshihan.learnEN2.Action.utils.ResourceUsageCountUtils;
import cucumber.api.java.it.Ma;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by qiu on 2/12/18.
 */
public class FindUsageAction extends AnAction {

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
                    if (ResourceUsageCountUtils.isTargetTagToCount(element)&&((XmlTagImpl) element).getAttributes().length>0) {
                        String name=((XmlTag) element).getLocalName()+":"+((XmlTagImpl) element).getAttributes()[0].getValue();
                        int count=FindUsagesCompat.findUsage((XmlTag) element);
                        DataUtils.resourcesMap.put(name,count);
                        System.out.println(name+":"+count);

                    }
                }
            }
        });
//     pFile.accept(new PsiElementVisitor() {
//         @Override
//         public void visitElement(PsiElement element) {
//             super.visitElement(element);
//             System.out.println(element.getManager().getProject().getName());
//         }
//     });
    }
}
