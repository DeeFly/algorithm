package info.gaofei.algorithm.dynamics.simpleDynamic;

/**
 * 一些层数的台阶，每次只能上一阶或者两阶，给定阶数，求多多少种走法
 * Created by GaoQingming on 2018/12/11 0011.
 */
public class SimpleDynamic {
    public static void main(String[] args) {
        System.out.println(ways(10));
    }

    public static int ways(int count) {
        if (count < 1) {
            return 0;
        }
        if (count == 1) {
            return 1;
        }

        if (count == 2) {
            return 2;
        }

        int preStep1 = 2;
        int preStep2 = 1;
        int thisStep = 3;
        for (int i = 3; i <= count; i ++) {
            thisStep = preStep1 + preStep2;
            preStep2 = preStep1;
            preStep1 = thisStep;
        }

        return thisStep;
    }
}
