package com.longshihan.learnEN2;

import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.longshihan.learnEN2.Action.UsageCountLineProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class TestProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo> result) {
        NavigationGutterIconBuilder<PsiElement> builder =
                NavigationGutterIconBuilder.create(new UsageCountLineProvider.MyIcon(2))
                        .setTargets(element)
                        .setTooltipText("Navigate to a simple property");
        result.add(builder.createLineMarkerInfo(element));
    }
}
