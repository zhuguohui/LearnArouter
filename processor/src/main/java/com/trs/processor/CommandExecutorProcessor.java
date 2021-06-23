package com.trs.processor;

import com.example.annotation.CommandExecutor;
import com.google.auto.service.AutoService;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class CommandExecutorProcessor extends AbstractProcessor {
    /**
     * 生成文件的工具类
     */
    private Filer filer;
    /**
     * 打印信息
     */
    private Messager messager;


    private Map<String, ClassCreatorProxy> mProxyMap=new HashMap<>();


    /**
     * 一些初始化操作，获取一些有用的系统工具类
     *
     * @param processingEnv
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();

    }


    /**
     * 设置支持的版本
     *
     * @return 这里用最新的就好
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    /**
     * 设置支持的注解类型
     *
     * @return
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        //添加支持的注解
        HashSet<String> set = new HashSet<>();
        set.add(CommandExecutor.class.getCanonicalName());
        return set;
    }

    /**
     * 注解内部逻辑的实现
     * <p>
     * Element代表程序的一个元素，可以是package, class, interface, method.只在编译期存在
     * TypeElement：变量；TypeElement：类或者接口
     *
     * @param annotations
     * @param roundEnv
     * @return
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv){
        messager.printMessage(Diagnostic.Kind.NOTE, "processing...");

        //得到所有的注解
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(CommandExecutor.class);
        for (Element element : elements) {
            ExecutableElement executableElement = (ExecutableElement) element;
            TypeElement classElement = (TypeElement) executableElement.getEnclosingElement();
            String fullClassName = classElement.getQualifiedName().toString();
            Element commandElement = executableElement.getParameters().get(0).getEnclosingElement();
            //doCreateDocCommand(com.example.learnarouter.commond.impl.CreateDocCommand,android.content.Context)
            String mehtodDesc = commandElement.toString();
            String commandClassName= mehtodDesc.substring(mehtodDesc.indexOf("(")+1,mehtodDesc.indexOf(","));
            String commandClassSimpleName= commandClassName.substring(commandClassName.lastIndexOf(".")+1);
            ClassCreatorProxy proxy = mProxyMap.get(commandClassName);
            if (proxy == null) {
                proxy = new ClassCreatorProxy(commandClassName,commandClassSimpleName,fullClassName, executableElement.getSimpleName().toString(),element);
                mProxyMap.put(fullClassName, proxy);
            }

        }
        //通过遍历mProxyMap，创建java文件
        for (String key : mProxyMap.keySet()) {
            ClassCreatorProxy proxyInfo = mProxyMap.get(key);
            try {
                messager.printMessage(Diagnostic.Kind.NOTE, " --> create " + proxyInfo.getProxyClassFullName());
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(proxyInfo.getProxyClassFullName(), proxyInfo.getTypeElement());
                Writer writer = jfo.openWriter();
                writer.write(proxyInfo.generateJavaCode());
                writer.flush();
                writer.close();
            } catch (IOException e) {
                messager.printMessage(Diagnostic.Kind.NOTE, " --> create " + proxyInfo.getProxyClassFullName() + "error");
            }
        }

        messager.printMessage(Diagnostic.Kind.NOTE, "process finish ...");
        return true;
    }
}