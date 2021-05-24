package com.kk.excelreadpoc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.kk.excelreadpoc.model.User;
import com.kk.excelreadpoc.utils.ApplicationProperties;
import com.kk.excelreadpoc.utils.CommonUtils;

/**
 * App Start File
 *
 */
public class App {

	private static final String SEPARATOR = "-----------------------------------------------------";

	private static final String SOURCE_FILE_PROP_KEY = "app.sourcefile";

	private static final String SEARCH_FILE_PROP_KEY = "app.searchfile";

	private static final String RESULTS_FILE_PROP_KEY = "app.resultfile";

	private static final Logger logger = LogManager.getLogger(App.class);

	public static void main(String[] args) {

		logger.info(" Starting App Execution >>>>");
		try {
			final Map<String, User> userDataMap = readSourceFile();
			logger.debug("Printing user data from first file below: ");
			logger.debug(SEPARATOR);
			for (final Map.Entry<String, User> entries : userDataMap.entrySet()) {
				logger.debug(entries.getValue());
			}
			logger.debug(SEPARATOR);

			if (!userDataMap.isEmpty()) {
				final Map<String, User> filteredUserMap = searchAndFilterUsers(userDataMap);

				if (!filteredUserMap.isEmpty()) {
					logger.debug("Printing Filtered user data: ");
					logger.debug(SEPARATOR);
					for (final Map.Entry<String, User> filtered : filteredUserMap.entrySet()) {
						logger.debug(filtered.getValue());
					}
					logger.debug(SEPARATOR);

					writeFilterUsersToExcel(filteredUserMap);

				} else {
					logger.info("No users found with matching aadhar!!!");
				}
			}

		} catch (IOException e) {
			logger.error("Error while opening file / work book", e);
		}
		logger.info("App Execution Ended <<<<");
	}

	/**
	 * @return
	 * @throws IOException
	 * 
	 */
	private static Map<String, User> readSourceFile() throws IOException {

		// map to store user info
		Map<String, User> userDataMap;

		final String sourceFilePath = ApplicationProperties.INSTANCE.getValue(SOURCE_FILE_PROP_KEY);
		logger.debug("Opening file path :: " + sourceFilePath);
		// open file and workbook
		try (FileInputStream file = new FileInputStream(new File(sourceFilePath));
				Workbook workbook = new XSSFWorkbook(file);) {

			// get first sheet
			final Sheet sheet = workbook.getSheetAt(0);

			userDataMap = new HashMap<>();

			// for each row
			for (final Row row : sheet) {
				if (row.getRowNum() == 0) {
					// skipping first row
					continue;
				}
				if (row.getCell(0).getCellType() == CellType.NUMERIC && row.getCell(1).getCellType() == CellType.NUMERIC
						&& row.getCell(2).getCellType() == CellType.NUMERIC) {
					userDataMap.put(CommonUtils.getStringValue(row.getCell(0).getNumericCellValue()),
							new User(CommonUtils.getStringValue(row.getCell(0).getNumericCellValue()),
									CommonUtils.getStringValue(row.getCell(1).getNumericCellValue()),
									CommonUtils.getStringValue(row.getCell(2).getNumericCellValue())));
				} else {
					logger.error("Data should be type numeric in row : " + row.getRowNum());
				}

			} // end of rows
			logger.debug("Completed reading source file.");
		}
		return userDataMap;
	}

	private static Map<String, User> searchAndFilterUsers(final Map<String, User> userDataMap) throws IOException {

		final Map<String, User> filteredUserMap = new HashMap<>();

		final String searchFilePath = ApplicationProperties.INSTANCE.getValue(SEARCH_FILE_PROP_KEY);
		logger.debug("Opening file path :: " + searchFilePath);

		Map<String, User> searchUserDataMap;

		try (FileInputStream file = new FileInputStream(new File(searchFilePath));
				Workbook workbook = new XSSFWorkbook(file);) {

			// get first sheet
			final Sheet sheet = workbook.getSheetAt(0);

			// map to store user info
			searchUserDataMap = new HashMap<>();

			// for each row
			for (final Row row : sheet) {
				if (row.getRowNum() == 0) {
					// skipping first row
					continue;
				}

				if (row.getCell(0).getCellType() == CellType.NUMERIC && row.getCell(1).getCellType() == CellType.NUMERIC
						&& row.getCell(2).getCellType() == CellType.NUMERIC) {
					final User user = new User(CommonUtils.getStringValue(row.getCell(0).getNumericCellValue()),
							CommonUtils.getStringValue(row.getCell(1).getNumericCellValue()),
							CommonUtils.getStringValue(row.getCell(2).getNumericCellValue()));

					searchUserDataMap.put(CommonUtils.getStringValue(row.getCell(0).getNumericCellValue()), user);
				} else {
					logger.error("Data should be type numeric in row : " + row.getRowNum());
				}

			} // end of rows
			logger.debug("Completed reading search file.");
		}

		filterData(userDataMap, filteredUserMap, searchUserDataMap);
		return filteredUserMap;
	}

	/**
	 * @param userDataMap
	 * @param filteredUserMap
	 * @param searchUserDataMap
	 */
	private static void filterData(final Map<String, User> userDataMap, final Map<String, User> filteredUserMap,
			Map<String, User> searchUserDataMap) {
		if (!searchUserDataMap.isEmpty()) {
			logger.debug("Printing Search file user data below: ");
			logger.debug(SEPARATOR);
			for (final Map.Entry<String, User> filtered : searchUserDataMap.entrySet()) {
				logger.debug(filtered.getValue());
			}
			logger.debug(SEPARATOR);

			for (final Map.Entry<String, User> entries : userDataMap.entrySet()) {
				if (searchUserDataMap.containsKey(entries.getKey())) {
					final User user = entries.getValue();
					filteredUserMap.put(entries.getKey(), user);
				} else {
					logger.debug("Aadhar not found in search file: " + entries.getKey());
				}
			}
		}
	}

	private static void writeFilterUsersToExcel(Map<String, User> filteredUserMap) throws IOException {

		final String outputFilePath = ApplicationProperties.INSTANCE.getValue(RESULTS_FILE_PROP_KEY);
		logger.debug("Writing filtered user data into file :: " + outputFilePath);
		try (Workbook workbook = new XSSFWorkbook();) {

			final Sheet sheet = workbook.createSheet("Filtered User");

			final XSSFFont font = ((XSSFWorkbook) workbook).createFont();
			font.setFontName("Arial");
			font.setFontHeightInPoints((short) 16);
			font.setBold(true);

			// create first row and set header info
			Row header = sheet.createRow(0);

			Cell headerCell = header.createCell(0);
			headerCell.setCellValue("Aadhar");

			headerCell = header.createCell(1);
			headerCell.setCellValue("Reference Number");

			headerCell = header.createCell(2);
			headerCell.setCellValue("USPC");

			CellStyle style = workbook.createCellStyle();
			style.setWrapText(true);

			int rowCount = 1;
			for (final Map.Entry<String, User> filtered : filteredUserMap.entrySet()) {
				Row row = sheet.createRow(rowCount);

				Cell cell = row.createCell(0);
				cell.setCellValue(filtered.getValue().getAadhar());
				cell.setCellStyle(style);

				cell = row.createCell(1);
				cell.setCellValue(filtered.getValue().getRefNum());
				cell.setCellStyle(style);

				cell = row.createCell(2);
				cell.setCellValue(filtered.getValue().getUscp());
				cell.setCellStyle(style);
				rowCount++;

			}

			try (FileOutputStream outputStream = new FileOutputStream(outputFilePath);) {
				workbook.write(outputStream);
			}

			logger.debug("Writing process completed.");
		}
	}
}
