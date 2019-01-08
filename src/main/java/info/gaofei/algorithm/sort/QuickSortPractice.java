package info.gaofei.algorithm.sort;

/**
 * Created by GaoQingming on 2019/1/8 0008.
 */
public class QuickSortPractice {
    public static void main(String[] args) {
        int[] array = {87,112,234,2346234,23,0,2, 34, 8, 9, 21, 456, 876, 12, 35, 86, 434};
        quickSort(array);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public static void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int left, int right) {

        if (left >= right) {
            return;
        }
        int end = right;
        int start = left;

        int middle = array[(left + right) / 2];
        while (left < right) {
            while (left < right && array[left] < middle) {
                left++;
            }
            while (right > left && array[right] > middle) {
                right--;
            }

            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
        }

        quickSort(array, start, left);
        quickSort(array, left + 1, end);
    }
}
