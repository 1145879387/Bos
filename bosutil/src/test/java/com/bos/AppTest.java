package com.bos;


import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@org.junit.Test
//	测试可能会找不到文件，容易挂机
	public void fun() throws IOException {
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(new FileInputStream(new File("E:\\java项目\\BOS-day05\\BOS-day05\\资料\\区域导入测试数据.xls")));
		HSSFSheet sheetAt = hssfWorkbook.getSheetAt(0);
//		遍历标签页里所有的行
		for (Row cells : sheetAt) {
//			获得第一行的数据
			int rowNum = cells.getRowNum();
//			HSSFRow row = sheetAt.getRow(0);
			if (rowNum == 0) {
				continue;
			}
			System.out.println();
			for (Cell cell : cells) {
//				这是每一个单元格里的数据
				String stringCellValue = cell.getStringCellValue();
				System.out.print(stringCellValue + " ");
			}
		}
	}
}
