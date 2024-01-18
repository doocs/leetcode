class Solution {
    private int[] arr;
    private int k;

    public int[] getLeastNumbers(int[] arr, int k) {
        int n = arr.length;
        this.arr = arr;
        this.k = k;
        return k == n ? arr : quickSort(0, n - 1);
    }

    private int[] quickSort(int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) {
                --j;
            }
            while (i < j && arr[i] <= arr[l]) {
                ++i;
            }
            swap(i, j);
        }
        swap(i, l);
        if (k < i) {
            return quickSort(l, i - 1);
        }
        if (k > i) {
            return quickSort(i + 1, r);
        }
        return Arrays.copyOf(arr, k);
    }

    private void swap(int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}