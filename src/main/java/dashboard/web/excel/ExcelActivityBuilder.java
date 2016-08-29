package dashboard.web.excel;
import java.util.List;
import java.util.Map;
 
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import dashboard.db.ASDataFormat;
import dashboard.db.jpa.Activity;
 
/**
 * This class builds an Excel spreadsheet document using Apache POI library.
 * @author www.codejava.net
 *
 */
public class ExcelActivityBuilder extends AbstractXlsView  {
 
	
//    protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)throws Exception {
//       
//
//    	
////    	org.apache.poi.util.TempFile.setTempFileCreationStrategy(new
////    			org.apache.poi.util.TempFile.DefaultTempFileCreationStrategy(new
////    			File("/var/tmp")))
//    	
//    	// get data model which is passed by the Spring container
//        List<Activity> listBooks = (List<Activity>) model.get("listActivity");
//       
//        // create a new Excel sheet
//        HSSFSheet sheet = workbook.createSheet("Activity");
//        //this.getTempDir();
//        sheet.setDefaultColumnWidth(30);
//         
//        // create style for header cells
//        CellStyle style = workbook.createCellStyle();
//        Font font = workbook.createFont();
//        font.setFontName("Arial");
//        style.setFillForegroundColor(HSSFColor.BLUE.index);
//        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
//        font.setColor(HSSFColor.WHITE.index);
//        style.setFont(font);
//         
//        // create header row
//        HSSFRow header = sheet.createRow(0);
//         
//        header.createCell(0).setCellValue("ID");
//        header.getCell(0).setCellStyle(style);
//         
//        header.createCell(1).setCellValue("Descrizione");
//        header.getCell(1).setCellStyle(style);
//         
//        header.createCell(2).setCellValue("Published Date");
//        header.getCell(2).setCellStyle(style);
//         
//        
//        // create data rows
//        int rowCount = 1;
//         
//        for (Activity aBook : listBooks) {
//            HSSFRow aRow = sheet.createRow(rowCount++);
//            aRow.createCell(0).setCellValue(aBook.getId());
//            aRow.createCell(1).setCellValue(aBook.getDescription());
//            aRow.createCell(2).setCellValue(aBook.getCreationdate());
//           
//        }
//    }

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {
		// get data model which is passed by the Spring container
        List<Activity> listBooks = (List<Activity>) model.get("listActivity");
       
        if (listBooks!=null){
	        // create a new Excel sheet
	        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Activity");
	        //this.getTempDir();
	        sheet.setDefaultColumnWidth(30);
	         
	        // create style for header cells
	        CellStyle style = workbook.createCellStyle();
	        Font font = workbook.createFont();
	        font.setFontName("Arial");
	        style.setFillForegroundColor(HSSFColor.BLUE.index);
	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	         
	        // create header row
	        HSSFRow header = sheet.createRow(0);
	        
	        String [] headers={"Id","Data creazione","Data inizio","Data fine","Descrizione","Assegnato a","Stato","Risultato","Descrizione errore"};
	        int h=0;
	        for(String name:headers){
		        header.createCell(h).setCellValue(name);
		        header.getCell(h).setCellStyle(style);
		        h++;
	        }

	        // create data rows
	        int rowCount = 1;
	         
	        for (Activity aBook : listBooks) {
	            HSSFRow aRow = sheet.createRow(rowCount++);
	            aRow.createCell(0).setCellValue(aBook.getId());
	            aRow.createCell(1).setCellValue(ASDataFormat.dateToString(aBook.getCreationdate(),"dd-MM-yyyy HH:mm"));
	            aRow.createCell(2).setCellValue(ASDataFormat.dateToString(aBook.getStartdate(),"dd-MM-yyyy HH:mm"));
	            aRow.createCell(3).setCellValue(ASDataFormat.dateToString(aBook.getEnddate(),"dd-MM-yyyy HH:mm"));
	            aRow.createCell(4).setCellValue(aBook.getDescription());
	            aRow.createCell(5).setCellValue(aBook.getOwner());
	            aRow.createCell(6).setCellValue(aBook.getStatus().toString());
	            aRow.createCell(7).setCellValue(aBook.getResult()!=null ? ""+aBook.getResult():"");
	            aRow.createCell(8).setCellValue(aBook.getFaildescription());
	           
	        }
	        
        
        }
		
	}
 
}