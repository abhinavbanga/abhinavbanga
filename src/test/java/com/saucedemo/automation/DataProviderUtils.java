package com.saucedemo.automation;

import com.saucedemo.selenium.pom.TestCaseDto;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.saucedemo.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataProviderUtils {
    @SuppressWarnings("unchecked")
    public static <T extends TestCaseDto> List<T> getDataObjectList(Class<? extends TestCaseDto> clazz, String fileName, String testCaseCode) {

        BeanListProcessor rowProcessor = new BeanListProcessor<>(clazz);

        try {
            getCsvParser(rowProcessor).parse(new FileReader(getFile(fileName)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        List<TestCaseDto> testDataList = rowProcessor.getBeans();

        if (StringUtils.isEmpty(testCaseCode)) {
            return (List<T>) testDataList;
        }

        return (List<T>) testDataList
                .stream()
                .filter(data -> testCaseCode.equals(data.getTestCaseCode()))
                .collect(Collectors.toList());
    }

    public static <T> Object[][] getDataObjectArray(Class<? extends TestCaseDto> clazz, String fileName, String testCaseCode) {

        BeanListProcessor rowProcessor = new BeanListProcessor<>(clazz);

        try {
            getCsvParser(rowProcessor).parse(new FileReader(getFile(fileName)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        List<TestCaseDto> testDataList = rowProcessor.getBeans();

        if (StringUtils.isEmpty(testCaseCode)) {
            return transpose(testDataList);
        }

        List<TestCaseDto> filteredList = testDataList
                .stream()
                .filter(data -> testCaseCode.equals(data.getTestCaseCode()))
                .collect(Collectors.toList());

        return transpose(filteredList);
    }

    public static <T> Object[][] getStringList(String fileName) {

        List<String> stringList = new LinkedList<>();
        try (Stream<String> stream = Files.lines(Paths.get(getFile(fileName)))) {
            stream.forEach(str -> {
                if (!str.startsWith("--") && StringUtils.isNotEmpty(str.trim())) { // '--' will be used to comment a line
                    stringList.add(str);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return transpose(stringList);
    }

    private static CsvParser getCsvParser(BeanListProcessor<TestCaseDto> rowProcessor) {
        CsvParserSettings parserSettings = new CsvParserSettings();
        parserSettings.getFormat().setLineSeparator("\n");
        parserSettings.setProcessor(rowProcessor);
        parserSettings.setHeaderExtractionEnabled(true);

        return new CsvParser(parserSettings);
    }

    /**
     * This method converts a List<T> into 2D array<T> of one column.
     * e.g: [0][val]
     * [1][val]
     * [1][val]
     */
    public static <T> Object[][] transpose(List<T> dataList) {

        Object[][] dataArray = new Object[dataList.size()][1];

        for (int i = 0; i < dataList.size(); i++) {
            dataArray[i][0] = dataList.get(i);
        }
        return dataArray;
    }

    private static String getFile(String fileName) {
        String DEFAULT_PATH = "src/test/resources/csvData/";

        return new File(DEFAULT_PATH + fileName).getAbsolutePath();
    }
}
