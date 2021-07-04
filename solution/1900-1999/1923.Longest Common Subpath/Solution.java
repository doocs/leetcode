class Solution {
    int N = 100010;
    long[] h = new long[N];
    long[] p = new long[N];
    private int[][] paths;
    Map<Long, Integer> cnt = new HashMap<>();
    Map<Long, Integer> inner = new HashMap<>();

    public int longestCommonSubpath(int n, int[][] paths) {
        int left = 0, right = N;
        for (int[] path : paths) {
            right = Math.min(right, path.length);
        }
        this.paths = paths;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int mid) {
        cnt.clear();
        inner.clear();
        p[0] = 1;
        for (int j = 0; j < paths.length; ++j) {
            int n = paths[j].length;
            for (int i = 1; i <= n; ++i) {
                p[i] = p[i - 1] * 133331;
                h[i] = h[i - 1] * 133331 + paths[j][i - 1];
            }
            for (int i = mid; i <= n; ++i) {
                long val = get(i - mid + 1, i);
                if (!inner.containsKey(val) || inner.get(val) != j) {
                    inner.put(val, j);
                    cnt.put(val, cnt.getOrDefault(val, 0) + 1);
                }
            }
        }
        int max = 0;
        for (int val : cnt.values()) {
            max = Math.max(max, val);
        }
        return max == paths.length;
    }

    private long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }
}