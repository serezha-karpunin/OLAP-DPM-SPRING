package com.eltech.olap.demo.service;

import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.pivot.CustomPivotModel;

import java.io.Serializable;

public interface PivotModelService {
    CustomPivotModel executeMdxQuery(Serializable bookmark, String mdxQuery);

    CustomPivotModel executeCommand(Serializable bookmark, Command command);
}
