class Solution {
    public boolean isCovered(int[][] ranges, int left, int right) {
        int[] diff = new int[52];
        for (int[] range : ranges) {
            int l = range[0], r = range[1];
            ++diff[l];
            --diff[r + 1];
        }
        int cur = 0;
        for (int i = 0; i < diff.length; ++i) {
            cur += diff[i];
            if (i >= left && i <= right && cur == 0) {
                return false;
            }
        }
        return true;
    }
}