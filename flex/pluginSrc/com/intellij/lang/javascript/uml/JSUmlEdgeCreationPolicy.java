package com.intellij.lang.javascript.uml;

import com.intellij.diagram.DiagramEdgeCreationPolicy;
import com.intellij.diagram.DiagramNode;
import com.intellij.lang.javascript.psi.ecmal4.JSAttributeList;
import com.intellij.lang.javascript.psi.ecmal4.JSClass;
import com.intellij.lang.javascript.refactoring.util.JSRefactoringUtil;
import org.jetbrains.annotations.NotNull;

public class JSUmlEdgeCreationPolicy implements DiagramEdgeCreationPolicy<Object> {

  public boolean acceptSource(@NotNull final DiagramNode<Object> source) {
    if (!(source.getIdentifyingElement() instanceof JSClass)) return false;
    final JSClass clazz = (JSClass)source.getIdentifyingElement();
    JSAttributeList attributeList = clazz.getAttributeList();
    if (attributeList != null && attributeList.hasModifier(JSAttributeList.ModifierType.FINAL)) return false;
    if (JSRefactoringUtil.isInLibrary(clazz)) return false;
    return true;
  }

  public boolean acceptTarget(@NotNull final DiagramNode<Object> target) {
    if (!(target.getIdentifyingElement() instanceof JSClass)) return false;
    final JSClass clazz = (JSClass)target.getIdentifyingElement();
    JSAttributeList attributeList = clazz.getAttributeList();
    if (attributeList != null && attributeList.hasModifier(JSAttributeList.ModifierType.FINAL)) return false;
    return true;
  }
}
