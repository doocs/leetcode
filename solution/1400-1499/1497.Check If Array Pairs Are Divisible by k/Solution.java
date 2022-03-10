class Solution {
    public boolean canArrange(int[] arr, int k) {
        int[] mod = new int[k];
        for (int v : arr) {
            ++mod[(v % k + k) % k];
        }
        for (int i = 1; i < k; ++i) {
            if (mod[i] != mod[k - i]) {
                return false;
            }
        }
        return mod[0] % 2 == 0;
    }
}