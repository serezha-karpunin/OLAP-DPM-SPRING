package com.eltech.olap.demo.callback;

import com.eltech.olap.demo.domain.*;
import com.eltech.olap.demo.domain.table.*;
import org.pivot4j.ui.AbstractRenderCallback;
import org.pivot4j.ui.command.UICommand;
import org.pivot4j.ui.table.TableRenderCallback;
import org.pivot4j.ui.table.TableRenderContext;

import java.util.ArrayList;
import java.util.List;

public class ObjectMappingRenderCallback
        extends AbstractRenderCallback<TableRenderContext>
        implements TableRenderCallback {

    private Table table;
    private TableHeader header;
    private TableBody body;
    private TableRow row;
    private TableCell cell;

    public Table getTable() {
        return table;
    }

    @Override
    public void startTable(TableRenderContext context) {
        table = new Table();
    }

    @Override
    public void startHeader(TableRenderContext context) {
        header = new TableHeader();
        header.setRows(new ArrayList<>());
    }

    @Override
    public void endHeader(TableRenderContext context) {
        table.setHeader(header);
        header = null;
    }

    @Override
    public void startBody(TableRenderContext context) {
        body = new TableBody();
        body.setRows(new ArrayList<>());
    }

    @Override
    public void endBody(TableRenderContext context) {
        table.setBody(body);
        body = null;
    }

    @Override
    public void startRow(TableRenderContext context) {
        row = new TableRow();
        row.setCells(new ArrayList<>());
    }

    @Override
    public void startCell(TableRenderContext context) {
        cell = new TableCell();
        cell.setType(context.getCellType());
        cell.setColSpan(context.getColumnSpan());
        cell.setRowSpan(context.getRowSpan());
    }

    @Override
    public void endCell(TableRenderContext context) {
        row.getCells().add(cell);
        this.cell = null;
    }

    @Override
    public void endRow(TableRenderContext context) {
        if (header == null) {
            body.getRows().add(row);
        } else {
            header.getRows().add(row);
        }
        this.row = null;
    }

    @Override
    public void endTable(TableRenderContext context) {

    }

    @Override
    public void renderCommands(TableRenderContext context, List<UICommand<?>> uiCommands) {
        List<Command> commands = new ArrayList<>();

        for (UICommand<?> uiCommand : uiCommands) {
            Command command = new Command();
            command.setName(uiCommand.getName());
            command.setMode(uiCommand.getMode(context));
            command.setParameters(uiCommand.createParameters(context));

            commands.add(command);
        }
        cell.setCommands(commands);
    }

    @Override
    public void renderContent(TableRenderContext context, String label, Double value) {
        cell.setLabel(label);
        cell.setValue(value);
    }
}
