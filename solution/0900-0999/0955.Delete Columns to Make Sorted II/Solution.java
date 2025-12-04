class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs.length;
        int m = strs[0].length();
        boolean[] st = new boolean[n - 1];
        int ans = 0;
        for (int j = 0; j < m; ++j) {
            boolean mustDel = false;
            for (int i = 0; i < n - 1; ++i) {
                if (!st[i] && strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    mustDel = true;
                    break;
                }
            }
            if (mustDel) {
                ++ans;
            } else {
                for (int i = 0; i < n - 1; ++i) {
                    if (!st[i] && strs[i].charAt(j) < strs[i + 1].charAt(j)) {
                        st[i] = true;
                    }
                }
            }
        }
        return ans;
    }
}
