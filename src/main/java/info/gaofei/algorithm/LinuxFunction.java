package info.gaofei.algorithm;

import java.io.*;
import java.util.*;

/**
 * Created by GaoQingming on 2019/1/9.
 */
public class LinuxFunction {
    public static void main(String[] args) throws IOException {
        String path = "D:\\data\\test.txt";
        String pattern = "Login";
        List<String> rows = getRows(path, pattern);
        Map<String, Integer> rowWithRepeatTimes = rowWithRepeatTimes(rows);

        printByOrder(rowWithRepeatTimes, false);
    }

    /**
     * 按照重复次数，根据order输出map中的字符串
     * @param rowWithRepeatTimes
     * @param order true：升序 false：降序
     */
    private static void printByOrder(Map<String, Integer> rowWithRepeatTimes, boolean order) {
        if (order) {
            printASC(rowWithRepeatTimes);
        } else {
            printDESC(rowWithRepeatTimes);
        }

    }

    private static void printDESC(Map<String, Integer> rowWithRepeatTimes) {
        while (!rowWithRepeatTimes.isEmpty()) {
            String row = findRowWithMaxRepeatTimes(rowWithRepeatTimes);
            System.out.println(row);
        }
    }

    private static void printASC(Map<String, Integer> rowWithRepeatTimes) {

    }

    private static String findRowWithMaxRepeatTimes(Map<String, Integer> rowWithRepeatTimes) {
        Integer count = 0;
        String maxRepeatedString = "";
        for (Map.Entry<String, Integer> entry : rowWithRepeatTimes.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                maxRepeatedString = entry.getKey();
            }
        }

        rowWithRepeatTimes.remove(maxRepeatedString);
        return maxRepeatedString;
    }

    private static Map<String,Integer> rowWithRepeatTimes(List<String> rows) {
        if (rows == null) {
            return null;
        }

        Map<String, Integer> result = new HashMap<>();
        Iterator<String> iterator = rows.iterator();
        while (iterator.hasNext()) {
            String row = iterator.next();
            Integer repeatCount = result.get(row);
            if (repeatCount == null) {
                repeatCount = 1;
            } else {
                repeatCount++;
            }
            result.put(row, repeatCount);
        }

        return result;
    }

    private static List<String> getRows(String path, String pattern) throws IOException {
        List<String> result = new LinkedList<>();
        File file = new File(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String row;
        while ((row = bufferedReader.readLine()) != null) {
            if (row.contains(pattern)) {
                result.add(row);
            }
        }
        return result;
    }
}
