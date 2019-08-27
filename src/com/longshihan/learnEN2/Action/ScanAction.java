package com.longshihan.learnEN2.Action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.psi.PsiManager;
import com.longshihan.learnEN2.Action.compat.FindUsagesImpl171;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ScanAction  extends AnAction {
    Project project;
    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        project=anActionEvent.getProject();
        if (project==null){
            return;
        }
        VirtualFile virtualFile=anActionEvent.getData(PlatformDataKeys.VIRTUAL_FILE);
        if (virtualFile==null){
            return;
        }
        File file= new File(virtualFile.getPath());
        dealFileCount(file);
        System.out.println("cccc");
    }

    public void dealFileCount(File file){
        if (file==null||project==null){
            return;
        }
        if (file.isDirectory()){
            //如果是目录
            File file1[]=file.listFiles();
            if (file1!=null&&file1.length>0){
                for (File f : file1) {
                    dealFileCount(f);
                }
            }
        }else {
            //如果不是目录
            VirtualFile virtualFile1= LocalFileSystem.getInstance().findFileByIoFile(file);
            if (virtualFile1==null){
                return;
            }
            int count= FindUsagesImpl171.getInstance().findUsage(PsiManager.getInstance(project).findFile(virtualFile1));
            System.out.println(file.getPath()+":"+count);
        }
    }
}
