class Solution {
    public int visibleMountains(int[][] peaks) {
        int n = peaks.length;
        int[][] arr = new int[n][2];
        Map<String, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            int x = peaks[i][0], y = peaks[i][1];
            arr[i] = new int[] {x - y, x + y};
            cnt.merge((x - y) + "" + (x + y), 1, Integer::sum);
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int[] e : arr) {
            int l = e[0], r = e[1];
            if (r <= cur) {
                continue;
            }
            cur = r;
            if (cnt.get(l + "" + r) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}