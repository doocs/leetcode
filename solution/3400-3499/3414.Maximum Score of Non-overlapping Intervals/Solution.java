class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; ++i) {
            List<Integer> e = intervals.get(i);
            arr[i] = new int[] {e.get(0), e.get(1), e.get(2), i};
        }
        Arrays.sort(arr,
            (a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(a[1], b[1]));
        int[] nxt = new int[n];
        for (int i = 0; i < n; ++i) {
            nxt[i] = search(arr, arr[i][1], i + 1);
        }
        long[][] f = new long[n + 1][5];
        int[][][] g = new int[n + 1][5][];
        for (int k = 0; k < 5; ++k) {
            g[n][k] = new int[0];
        }
        for (int i = n - 1; i >= 0; --i) {
            g[i][0] = new int[0];
            for (int k = 1; k < 5; ++k) {
                long s1 = f[i + 1][k];
                int[] a1 = g[i + 1][k];
                long s2 = f[nxt[i]][k - 1] + arr[i][2];
                int[] a2 = insert(g[nxt[i]][k - 1], arr[i][3]);
                if (s2 > s1 || (s2 == s1 && less(a2, a1))) {
                    f[i][k] = s2;
                    g[i][k] = a2;
                } else {
                    f[i][k] = s1;
                    g[i][k] = a1;
                }
            }
        }
        return g[0][4];
    }

    private int search(int[][] arr, int x, int l) {
        int r = arr.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid][0] > x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int[] insert(int[] a, int x) {
        int n = a.length;
        int[] b = new int[n + 1];
        int i = 0;
        while (i < n && a[i] < x) {
            b[i] = a[i];
            ++i;
        }
        b[i] = x;
        while (i < n) {
            b[i + 1] = a[i];
            ++i;
        }
        return b;
    }

    private boolean less(int[] a, int[] b) {
        int m = Math.min(a.length, b.length);
        for (int i = 0; i < m; ++i) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }
        return a.length < b.length;
    }
}
