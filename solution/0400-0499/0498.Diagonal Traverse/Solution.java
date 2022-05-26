class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] ans = new int[m * n];
        int k = 0;
        List<Integer> t = new ArrayList<>();
        for (int i = 0; i < m + n - 1; ++i) {
            int r = i < n ? 0 : i - n + 1;
            int c = i < n ? i : n - 1;
            while (r < m && c >= 0) {
                t.add(mat[r][c]);
                ++r;
                --c;
            }
            if (i % 2 == 0) {
                Collections.reverse(t);
            }
            for (int v : t) {
                ans[k++] = v;
            }
            t.clear();
        }
        return ans;
    }
}