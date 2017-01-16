package de.jura.util;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import de.jura.res.data.PrimaryResource;

public class ExportExcel {

	public static void exportToExcel(List<PrimaryResource> pres) {
		
		

		HSSFWorkbook hwb = new HSSFWorkbook();
		HSSFSheet sheet = hwb.createSheet("new sheet");
		int rowNum = 0;
		HSSFRow rowhead = sheet.createRow((short) rowNum);
		rowhead.createCell((short) 0).setCellValue("id");
		rowhead.createCell((short) 1).setCellValue("resource_id");
		rowhead.createCell((short) 2).setCellValue("resource_name");
		rowhead.createCell((short) 3).setCellValue("resource_type");
		rowhead.createCell((short) 4).setCellValue("resource_company");
		rowhead.createCell((short) 5).setCellValue("installation_date");
		rowhead.createCell((short) 6).setCellValue("ip_address");
		rowhead.createCell((short) 7).setCellValue("mac_address");
		rowhead.createCell((short) 8).setCellValue("faculty");

		for (PrimaryResource pre : pres) {

			HSSFRow row = sheet.createRow((short) ++rowNum );
			System.out.println("The value of rownum is:"+rowNum);

			row.createCell((short) 0).setCellValue((pre.getId()));
			row.createCell((short) 1).setCellValue(pre.getResource_id());
			row.createCell((short) 2).setCellValue(pre.getResource_name());
			row.createCell((short) 3).setCellValue(pre.getResource_type());
			row.createCell((short) 4).setCellValue(pre.getResource_company());
			row.createCell((short) 5).setCellValue(
					pre.getInstallation_date().toString());
			row.createCell((short) 6).setCellValue(pre.getIp_address());
			row.createCell((short) 7).setCellValue(pre.getMac_address());
			row.createCell((short) 8).setCellValue(pre.getFaculty());

		}

		String filename = "D:\\data.xls";
		FileOutputStream fileOut;

		try {
			fileOut = new FileOutputStream(filename);
			hwb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
