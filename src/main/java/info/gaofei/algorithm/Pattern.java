package info.gaofei.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by GaoQingming on 2019/1/9.
 */
public class Pattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String address = scanner.nextLine();
        boolean match = isMatch(pattern, address);
        System.out.println(match);
    }

    private static boolean isMatch(String pattern, String address) {
        if (pattern == null || address == null) {
            return false;
        }
        String[] addresses = address.split(" ");
        if (pattern.length() != addresses.length) {
            return false;
        }

        Map<String, String> dic = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            String thisPattern = String.valueOf(pattern.charAt(i));
            String expectedString = dic.get(thisPattern);
            if (expectedString == null) {
                dic.put(thisPattern, addresses[i]);
            } else {
                if (!expectedString.equals(addresses[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
