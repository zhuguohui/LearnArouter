package com.trs.processor;

import javax.lang.model.element.Element;

public class ClassCreatorProxy {

    static final String muban="package $packName;\n" +
            "\n" +
            "import android.content.Context;\n" +
            "\n" +
            "\n" +
            "public class CreateDocCommandExecutor {\n" +
            "    public static void execute($CommandFullName command, Context context){\n" +
            "        $clazzName.$methodName(command,context);\n" +
            "    }\n" +
            "}\n";

    String commandClassName;
    String className;
    String methodName;
    String packName="com.trs.command";
    Element element;
    String commandSimpleName;
    public ClassCreatorProxy(String commandClassName,String commandSimpleName, String className, String methodName, Element element) {
        this.commandClassName=commandClassName;
        this.className=className;
        this.methodName=methodName;
        this.element=element;
        this.commandSimpleName=commandSimpleName;
    }

    public CharSequence getProxyClassFullName() {
        return packName+"."+commandSimpleName+"Executor";
    }

    public Element getTypeElement() {
        return element;
    }

    public String generateJavaCode() {
        String clazz=muban.replace("$packName",packName).replace("$CommandFullName",commandClassName)
                .replace("$clazzName",className)
                .replace("$methodName",methodName);
        return clazz;
    }
}
