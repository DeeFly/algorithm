package info.gaofei.algorithm.sort;

/**
 * Created by GaoQingming on 2018/10/2.
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] array = {87, 8, 34, 8, 9, 21, 456, 876, 12, 35, 86, 434};
        quickSort.quickSort(array, 0, array.length - 1);
        for (int i : array) {
            System.out.println(i);
        }
    }

    public void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (right + left) / 2;
            int l = left;
            int r = right;
            swampValue(array, left, mid);
            int tempValue = array[left];
            while (l < r) {
                while (l < r && array[r] >= tempValue) {
                    --r;
                }
                array[l] = array[r];    //将 比tempValue小的移到低端
                while (l < r && array[l] <= tempValue) {
                    l++;
                }
                array[r] = array[l];    //将比temp大的移到高端
            }
            array[l] = tempValue;
            quickSort(array, left, l - 1);
            quickSort(array, l + 1, right);
        }
    }

    private void swampValue(int[] array, int l, int r) {
        int tempV = array[l];
        array[l] = array[r];
        array[r] = tempV;
    }
}
