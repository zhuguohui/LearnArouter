package com.example.learnarouter.commond.impl;

import com.example.learnarouter.bean.DocItem;
import com.example.learnarouter.commond.base.AbstractCommand;

public class CreateDocCommand  extends AbstractCommand<DocItem> {

    String docType;

    public CreateDocCommand(String docType) {
        this.docType = docType;
    }


    public String getDocType() {
        return docType;
    }

    @Override
    public String getDesc() {
        return "创建文档";
    }

}
