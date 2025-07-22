class Solution {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        int[][] arr = new int[n][0];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] {x[i], y[i]};
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int ans = 0;
        Set<Integer> vis = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int a = arr[i][0], b = arr[i][1];
            if (vis.add(a)) {
                ans += b;
                if (vis.size() == 3) {
                    return ans;
                }
            }
        }
        return -1;
    }
}