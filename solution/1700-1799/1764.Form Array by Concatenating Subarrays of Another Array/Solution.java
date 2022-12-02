class Solution {
    public boolean canChoose(int[][] groups, int[] nums) {
        int n = groups.length, m = nums.length;
        int i = 0;
        for (int j = 0; i < n && j < m;) {
            if (check(groups[i], nums, j)) {
                j += groups[i].length;
                ++i;
            } else {
                ++j;
            }
        }
        return i == n;
    }

    private boolean check(int[] a, int[] b, int j) {
        int m = a.length, n = b.length;
        int i = 0;
        for (; i < m && j < n; ++i, ++j) {
            if (a[i] != b[j]) {
                return false;
            }
        }
        return i == m;
    }
}