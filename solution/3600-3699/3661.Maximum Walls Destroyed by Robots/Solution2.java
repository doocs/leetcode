class Solution {
    public int maxWalls(int[] robots, int[] distance, int[] walls) {
        int n = robots.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = robots[i];
            arr[i][1] = distance[i];
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(walls);
        int[][] f = new int[n][2];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < 2; ++j) {
                int left = arr[i][0] - arr[i][1];
                if (i > 0) {
                    left = Math.max(left, arr[i - 1][0] + 1);
                }
                int l = lowerBound(walls, left);
                int r = lowerBound(walls, arr[i][0] + 1);
                int ans = (i > 0 ? f[i - 1][0] : 0) + (r - l);
                int right = arr[i][0] + arr[i][1];
                if (i + 1 < n) {
                    if (j == 0) {
                        right = Math.min(right, arr[i + 1][0] - arr[i + 1][1] - 1);
                    } else {
                        right = Math.min(right, arr[i + 1][0] - 1);
                    }
                }
                l = lowerBound(walls, arr[i][0]);
                r = lowerBound(walls, right + 1);
                ans = Math.max(ans, (i > 0 ? f[i - 1][1] : 0) + (r - l));
                f[i][j] = ans;
            }
        }
        return f[n - 1][1];
    }

    private int lowerBound(int[] arr, int target) {
        int idx = Arrays.binarySearch(arr, target);
        if (idx < 0) {
            return -idx - 1;
        }
        return idx;
    }
}
