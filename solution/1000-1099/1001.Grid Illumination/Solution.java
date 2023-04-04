class Solution {
    private int n;
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        this.n = n;
        Set<Long> s = new HashSet<>();
        Map<Integer, Integer> row = new HashMap<>();
        Map<Integer, Integer> col = new HashMap<>();
        Map<Integer, Integer> diag1 = new HashMap<>();
        Map<Integer, Integer> diag2 = new HashMap<>();
        for (var lamp : lamps) {
            int i = lamp[0], j = lamp[1];
            if (s.add(f(i, j))) {
                merge(row, i, 1);
                merge(col, j, 1);
                merge(diag1, i - j, 1);
                merge(diag2, i + j, 1);
            }
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int k = 0; k < m; ++k) {
            int i = queries[k][0], j = queries[k][1];
            if (exist(row, i) || exist(col, j) || exist(diag1, i - j) || exist(diag2, i + j)) {
                ans[k] = 1;
            }
            for (int x = i - 1; x <= i + 1; ++x) {
                for (int y = j - 1; y <= j + 1; ++y) {
                    if (x < 0 || x >= n || y < 0 || y >= n || !s.contains(f(x, y))) {
                        continue;
                    }
                    s.remove(f(x, y));
                    merge(row, x, -1);
                    merge(col, y, -1);
                    merge(diag1, x - y, -1);
                    merge(diag2, x + y, -1);
                }
            }
        }
        return ans;
    }

    private void merge(Map<Integer, Integer> cnt, int x, int d) {
        if (cnt.merge(x, d, Integer::sum) == 0) {
            cnt.remove(x);
        }
    }

    private boolean exist(Map<Integer, Integer> cnt, int x) {
        return cnt.getOrDefault(x, 0) > 0;
    }

    private long f(long i, long j) {
        return i * n + j;
    }
}