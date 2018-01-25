package com.eltech.olap.demo.service;

import com.eltech.olap.demo.domain.Command;
import com.eltech.olap.demo.domain.QueryResult;

public interface PivotModelService {
    QueryResult executeMdxQuery(String mdxQuery);

    QueryResult executeCommand(String storedQuery, Command command);
}
