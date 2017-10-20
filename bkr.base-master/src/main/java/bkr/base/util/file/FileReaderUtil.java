package bkr.base.util.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import bkr.base.util.constants.CommonConstants;

/**
 * 文件读取Util类
 * 
 * @author chengd
 */
public final class FileReaderUtil {

    /**
     * txt文件内容读取
     * 
     * @param is
     *            文件流
     * @param charset
     *            编码
     * @return 文件内容list
     * @throws IOException
     *             例外
     */
    public static List<String> readTxtFile(InputStream is, String charset)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,
                charset));
        List<String> retval = new ArrayList<String>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            retval.add(line);
        }
        return retval;
    }

    /**
     * CSV文件内容读取
     * 
     * @param is
     *            文件流
     * @param charset
     *            编码
     * @return 文件内容list
     * @throws IOException
     *             例外
     */
    public static List<List<String>> readCsvFile(InputStream is, String charset)
            throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is,
                charset));
        List<List<String>> retval = new ArrayList<List<String>>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            retval.add(getSaxLineStringLst(line));
        }
        return retval;
    }

    /**
     * 
     * 行数据解析
     * 
     * @param line
     *            行対象
     * @return list
     */
    private static List<String> getSaxLineStringLst(String line) {
        List<String> subArr = separationCsvWithComma(line);
        return subArr;
    }

    /**
     * 
     * 行数据解析(逗号分隔)
     * 
     * @param line
     *            行字符串
     * @return subArr 分割后集合
     */
    private static List<String> separationCsvWithComma(String line) {

        List<String> subArr = new ArrayList<String>();

        if (line.indexOf(CommonConstants.COMMA.value()) == -1) {
            return subArr;
        }

        int count = 0;
        int startIndex = 0;

        for (int i = 0; i < line.length(); i++) {
            String str = String.valueOf(line.charAt(i));

            if (CommonConstants.COMMA.value().equals(str)) {
                if (count % 2 != 0) {
                    continue;
                } else {
                    subArr.add(line.substring(startIndex + 1, i));
                    startIndex = i;
                    if (i == line.length() - 1) {
                        subArr.add("");
                    }
                }
            } else if (i == line.length() - 1) {
                subArr.add(line.substring(startIndex + 1, line.length()));
            } else if ("\"".equals(str)) {
                count++;
            }
        }

        return subArr;
    }
}
