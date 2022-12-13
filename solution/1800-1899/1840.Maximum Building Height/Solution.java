class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> r = new ArrayList<>();
        r.addAll(Arrays.asList(restrictions));
        r.add(new int[] {1, 0});
        Collections.sort(r, (a, b) -> a[0] - b[0]);
        if (r.get(r.size() - 1)[0] != n) {
            r.add(new int[] {n, n - 1});
        }
        int m = r.size();
        for (int i = 1; i < m; ++i) {
            int[] a = r.get(i - 1), b = r.get(i);
            b[1] = Math.min(b[1], a[1] + b[0] - a[0]);
        }
        for (int i = m - 2; i > 0; --i) {
            int[] a = r.get(i), b = r.get(i + 1);
            a[1] = Math.min(a[1], b[1] + b[0] - a[0]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int[] a = r.get(i), b = r.get(i + 1);
            int t = (a[1] + b[1] + b[0] - a[0]) / 2;
            ans = Math.max(ans, t);
        }
        return ans;
    }
}