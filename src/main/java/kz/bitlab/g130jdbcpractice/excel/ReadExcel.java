package kz.bitlab.g130jdbcpractice.excel;

import jakarta.annotation.PostConstruct;
import kz.bitlab.g130jdbcpractice.entity.ApplicationRequest;
import kz.bitlab.g130jdbcpractice.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReadExcel {

    private final ApplicationRequestService applicationRequestService;

    @PostConstruct
    public void init() {
        List<ApplicationRequest> applicationRequests = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream("qwerty.xlsx");
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i < sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                ApplicationRequest applicationRequest = new ApplicationRequest();
                applicationRequest.setUserName(row.getCell(0).getStringCellValue());
                applicationRequest.setCommentary(row.getCell(2).getStringCellValue());
                applicationRequest.setPhone(String.valueOf(row.getCell(3).getNumericCellValue()));
                applicationRequest.setCourseName(row.getCell(1).getStringCellValue());
                applicationRequests.add(applicationRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (ApplicationRequest request : applicationRequests) {
            applicationRequestService.addApplicationRequest(request);
        }
    }
}
