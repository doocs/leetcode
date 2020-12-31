class Solution {
    public int[] constructArr(int[] a) {
        int n = a.length;
        int[] output = new int[n];
        for (int i = 0, left = 1; i < n; ++i) {
            output[i] = left;
            left *= a[i];
        }
        for (int i = n - 1, right = 1; i >= 0; --i) {
            output[i] *= right;
            right *= a[i];
        }
        return output;
    }
}