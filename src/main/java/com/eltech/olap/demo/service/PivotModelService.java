package com.eltech.olap.demo.service;

import com.eltech.olap.demo.domain.Table;

public interface PivotModelService {
    Table getQueryResult(String mdxQuery);
}
