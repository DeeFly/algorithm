package info.gaofei.algorithm.dynamics.spaceSplitString;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 单词切割题
 * 题目为：
 * 给定一个字符串S和有效单词的字典D，请确定可以插入到S中的最小空格数，使得最终的字符串完全由D中的有效单词组成，并输出解。
 * 如果没有解则应该输出n/a
 * 例如
 * 输入
 * S = “ilikealibaba”
 * D = [“i”, “like”, “ali”, “liba”, “baba”, “alibaba”]
 * Example Output:
 * 输出
 * “i like alibaba”
 * 解释：
 * 字符串S可能被字典D这样拆分
 * “i like ali baba”
 * “i like alibaba”
 * ---------------------
 * 作者：Zpeg
 * 来源：CSDN
 * 原文：https://blog.csdn.net/qq_28351465/article/details/77587114
 * 版权声明：本文为博主原创文章，转载请附上博文链接！
 * Created by GaoQingming on 2019/1/9.
 */
public class SpaceSplitString {
    public static void main(String[] args) {
        String s = "ilikealibaba";
        Set<String> dic = new HashSet<>();
        dic.add("i");
        dic.add("like");
        dic.add("ali");
        dic.add("baba");
        dic.add("alibaba");
        Wrapper wrapper = new SpaceSplitString().find(s, dic);
        System.out.println(wrapper.spaceCount);
        wrapper.print();
    }

    public Wrapper find(String string, Set<String> dic) {
        Wrapper result = new Wrapper();
        if (dic.contains(string)) {
            result.head = string;
            result.spaceCount = 0;
            result.best = null;
            return result;
        }

        Map<String, Wrapper> subMap = new HashMap<>();
        String origin = string;
        String temp = "";
        for (int i = 0; i < origin.length() - 1; i++) {
            temp = temp + origin.charAt(i);
            if (dic.contains(temp)) {
                subMap.put(temp, find(origin.substring(i + 1), dic));
            }
        }

        String sub = "";
        int spaceC = -1;
        for (Map.Entry<String, Wrapper> entry : subMap.entrySet()) {
            if (entry.getValue().spaceCount < spaceC || spaceC == -1) {
                sub = entry.getKey();
                spaceC = entry.getValue().spaceCount;
            }
        }

        result.spaceCount = spaceC + 1;
        result.head = sub;
        result.best = subMap.get(sub);
        return result;
    }

    private class Wrapper{
        private String head;
        private int spaceCount = 1;
        private Wrapper best;
        public void print() {
            if (this.spaceCount == 0) {
                System.out.print(head);
            } else {
                System.out.print(head + " ");
                this.best.print();
            }
        }
    }
}
