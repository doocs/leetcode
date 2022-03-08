class Solution {
    public int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + (s.charAt(i) == '*' ? 1 : 0);
        }
        int[] left = new int[n];
        int[] right = new int[n];
        for (int i = 0, l = -1; i < n; ++i) {
            if (s.charAt(i) == '|') {
                l = i;
            }
            left[i] = l;
        }
        for (int i = n - 1, r = -1; i >= 0; --i) {
            if (s.charAt(i) == '|') {
                r = i;
            }
            right[i] = r;
        }
        int[] ans = new int[queries.length];
        for (int k = 0; k < queries.length; ++k) {
            int i = right[queries[k][0]];
            int j = left[queries[k][1]];
            if (i >= 0 && j >= 0 && i < j) {
                ans[k] = presum[j] - presum[i + 1];
            }
        }
        return ans;
    }
}