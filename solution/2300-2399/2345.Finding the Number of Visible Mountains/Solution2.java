class Solution {
    public int visibleMountains(int[][] peaks) {
        int n = peaks.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int x = peaks[i][0], y = peaks[i][1];
            arr[i] = new int[] {x - y, x + y};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            int l = arr[i][0], r = arr[i][1];
            if (r <= cur) {
                continue;
            }
            cur = r;
            if (!(i < n - 1 && arr[i][0] == arr[i + 1][0] && arr[i][1] == arr[i + 1][1])) {
                ++ans;
            }
        }
        return ans;
    }
}