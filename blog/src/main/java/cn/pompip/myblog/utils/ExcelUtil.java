package cn.pompip.myblog.utils;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * HSSFWorkbook xls
 * XSSFWorkbook xlsx
 */
public class ExcelUtil {
    /**
     * 要求excel版本在2007以上
     *
     * @param file 文件信息
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(File file) throws Exception {

        List<List<Object>> list = new LinkedList<List<Object>>();
        Workbook xwb = new XSSFWorkbook(new FileInputStream(file));
        // 读取第一张表格内容
        Sheet sheet = xwb.getSheetAt(0);

        parse(list, sheet);
        return list;
    }
    /**
     * 要求excel版本在2007以上
     *
     * @param fileInputStream 文件信息
     * @return
     * @throws Exception
     */
    public static List<List<Object>> readExcel(FileInputStream fileInputStream) throws Exception {
        List<List<Object>> list = new LinkedList<List<Object>>();
        Workbook xwb = new XSSFWorkbook(fileInputStream);
        // 读取第一张表格内容
        Sheet sheet = xwb.getSheetAt(0);

        parse(list, sheet);
        return list;
    }

    private static void parse(List<List<Object>> list, Sheet sheet) {
        Row row;
        Cell cell;
        for (int i = (sheet.getFirstRowNum() + 1); i <= (sheet.getPhysicalNumberOfRows() - 1); i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            List<Object> linked = new LinkedList<Object>();
            for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++) {
                Object value = null;
                cell = row.getCell(j);
                if (cell == null) {
                    continue;
                }
                switch (cell.getCellType()) {
                    case STRING:
                        value = cell.getStringCellValue();
                        break;
                    case NUMERIC:

                        if (HSSFDateUtil.isCellDateFormatted(cell)) {
                            //System.out.println(cell.getNumericCellValue()+":日期格式："+cell.getCellStyle().getDataFormatString());
                            value = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                        } else {
                            //System.out.println(cell.getNumericCellValue()+":格式："+cell.getCellStyle().getDataFormatString());
                            value = cell.getNumericCellValue();
                        }
                        break;
                    case BOOLEAN:
                        value = cell.getBooleanCellValue();
                        break;
                    case BLANK:
                        break;
                    default:
                        value = cell.toString();
                }
                if (value != null && !value.equals("")) {
                    //单元格不为空，则加入列表
                    linked.add(value);
                }
            }
            if (linked.size()!= 0) {
                list.add(linked);
            }
        }
    }

    /**
     * 导出excel
     * @param excel_name 导出的excel路径（需要带.xlsx)
     * @param headList  excel的标题备注名称
     * @param fieldList excel的标题字段（与数据中map中键值对应）
     * @param dataList  excel数据
     * @throws Exception
     */
    public static void createExcel(String excel_name, String[] headList,
                                   String[] fieldList, List<Map<String, Object>> dataList)
            throws Exception {
        // 创建新的Excel 工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 在Excel工作簿中建一工作表，其名为缺省值
        Sheet sheet = workbook.createSheet();
        // 在索引0的位置创建行（最顶端的行）
        Row row = sheet.createRow(0);
        // 设置excel头（第一行）的头名称
        for (int i = 0; i < headList.length; i++) {

            // 在索引0的位置创建单元格（左上端）
            Cell cell = row.createCell(i);
            // 定义单元格为字符串类型
            cell.setCellType(CellType.STRING);
            // 在单元格中输入一些内容
            cell.setCellValue(headList[i]);
        }
        // ===============================================================
        //添加数据
        for (int n = 0; n < dataList.size(); n++) {
            // 在索引1的位置创建行（最顶端的行）
            Row row_value = sheet.createRow(n + 1);
            Map<String, Object> dataMap = dataList.get(n);
            // ===============================================================
            for (int i = 0; i < fieldList.length; i++) {

                // 在索引0的位置创建单元格（左上端）
                Cell cell = row_value.createCell(i);
                // 定义单元格为字符串类型
                cell.setCellType(CellType.STRING);
                // 在单元格中输入一些内容
                cell.setCellValue((dataMap.get(fieldList[i])).toString());
            }
            // ===============================================================
        }
        // 新建一输出文件流
        FileOutputStream fos = new FileOutputStream(excel_name);
        // 把相应的Excel 工作簿存盘
        workbook.write(fos);
        fos.flush();
        // 操作结束，关闭文件
        fos.close();
    }

}
