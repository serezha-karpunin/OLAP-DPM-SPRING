package com.eltech.olap.demo.service;

import com.eltech.olap.demo.domain.meta.PivotMetadata;
import org.pivot4j.PivotModel;
import org.pivot4j.ui.command.UICommand;
import org.pivot4j.ui.command.UICommandParameters;

public interface PivotModelService {
    PivotModel createPivotModel(String mdxQuery);

    PivotModel executeMdxQuery(String mdxQuery);

    PivotModel executeCommand(String storedQuery, UICommand<?> uiCommand, UICommandParameters commandParameters);

    PivotMetadata getMetadata(PivotModel model);
}