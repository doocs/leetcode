class Solution {
    public int[] getTriggerTime(int[][] increase, int[][] requirements) {
        int m = increase.length, n = requirements.length;
        int[][] s = new int[m + 1][3];
        for (int j = 0; j < 3; ++j) {
            for (int i = 0; i < m; ++i) {
                s[i + 1][j] = s[i][j] + increase[i][j];
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; ++i) {
            int left = 0, right = m + 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (check(s[mid], requirements[i])) {
                    ans[i] = mid;
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
        }
        return ans;
    }

    private boolean check(int[] a, int[] b) {
        for (int i = 0; i < 3; ++i) {
            if (a[i] < b[i]) {
                return false;
            }
        }
        return true;
    }
}
