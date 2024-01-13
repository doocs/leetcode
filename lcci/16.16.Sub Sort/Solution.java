class Solution {
    public int[] subSort(int[] array) {
        int n = array.length;
        int mi = Integer.MAX_VALUE, mx = Integer.MIN_VALUE;
        int left = -1, right = -1;
        for (int i = 0; i < n; ++i) {
            if (array[i] < mx) {
                right = i;
            } else {
                mx = array[i];
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (array[i] > mi) {
                left = i;
            } else {
                mi = array[i];
            }
        }
        return new int[] {left, right};
    }
}