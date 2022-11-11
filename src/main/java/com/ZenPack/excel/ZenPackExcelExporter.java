package com.ZenPack.excel;

import com.ZenPack.Dto.SearchFilterDto;
import com.ZenPack.model.ZenPack;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ZenPackExcelExporter {      //new one
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    private List<ZenPack> listZenPack;

    public ZenPackExcelExporter(List<ZenPack> listZenPack) {
        this.listZenPack = listZenPack;
        workbook = new XSSFWorkbook();

    }
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Long) {
            cell.setCellValue((Long) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }
    private void writeHeaderLine(SearchFilterDto excelRequest) {
        sheet = workbook.createSheet("ZenPack");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(20);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillBackgroundColor((short) 215);
        createCell(row, 0, "ZenPack Information", style);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        font.setFontHeightInPoints((short) (10));
        row = sheet.createRow(1);
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        int column_Count=0;
        if (Arrays.asList(excelRequest.getColumnsVisible()).contains("ZenPack Name")){
            createCell(row, column_Count, "ZenPack Name", style);
            column_Count++;
        } if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Created Date")) {
            createCell(row, column_Count, "Created Date", style);
            column_Count++;
        } if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Created By")) {
            createCell(row, column_Count, "Created By", style);
            column_Count++;
        }  if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Updated Time")) {
            createCell(row, column_Count, "Updated Time", style);
            column_Count++;
        }  if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Updated By")) {
            createCell(row, column_Count, "Updated By", style);
            column_Count++;
        }  if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Active")) {
            createCell(row, column_Count, "Active", style);
            column_Count++;
        }
        if (Arrays.asList(excelRequest.getColumnsVisible()).isEmpty()) {
            createCell(row, 0, "ZenPack Name", style);
            createCell(row, 1, "Created Date", style);
            createCell(row, 2, "Created By", style);
            createCell(row, 3, "Updated Time", style);
            createCell(row, 4, "Updated By", style);
            createCell(row, 5, "Active", style);
        }
    }
    private void writeDataLines(SearchFilterDto excelRequest) {
        int rowCount = 2;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(16);
        style.setFont(font);
        Date dNow = new Date();
        SimpleDateFormat ft =
                new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("Current Date: " + ft.format(dNow));
        for (ZenPack zen : listZenPack) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            if (Arrays.asList(excelRequest.getColumnsVisible()).contains("ZenPack Name")) {
                createCell(row, columnCount++, zen.getName(), style);
                }if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Created Date")) {
                    createCell(row, columnCount++, zen.getCreatedDate().toString(), style);
                } if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Created By")) {
                    createCell(row, columnCount++, zen.getCreatedBy(), style);
                } if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Updated Time")) {
                createCell(row, columnCount++, zen.getUpdatedTime().toString(), style);
                } if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Updated By")) {
                createCell(row, columnCount++, zen.getUpdatedBy(), style);
                } if (Arrays.asList(excelRequest.getColumnsVisible()).contains("Active")) {
                createCell(row, columnCount++, zen.getInActive(), style);
                }
                if (Arrays.asList(excelRequest.getColumnsVisible()).isEmpty()) {
                createCell(row, columnCount++, zen.getName(), style);
                createCell(row, columnCount++, zen.getCreatedDate().toString(), style);
                createCell(row, columnCount++, zen.getCreatedBy(), style);
                createCell(row, columnCount++, zen.getUpdatedTime().toString(), style);
                createCell(row, columnCount++, zen.getUpdatedBy(), style);
                createCell(row, columnCount++, zen.getInActive(), style);
            }
        }
    }
    public void export(SearchFilterDto excelRequest, HttpServletResponse response) throws IOException {
        writeHeaderLine(excelRequest);
        writeDataLines(excelRequest);
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
