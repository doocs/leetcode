class Solution {
    public int minWastedSpace(int[] packages, int[][] boxes) {
        int n = packages.length;
        Arrays.sort(packages);
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + packages[i];
        }

        long res = Long.MAX_VALUE;
        for (int[] box : boxes) {
            Arrays.sort(box);
            if (packages[n - 1] > box[box.length - 1]) {
                continue;
            }
            long t = 0;
            int low = 0;
            for (int b : box) {
                int idx = searchRight(packages, b, low);
                t += ((idx - low) * (long) b - (preSum[idx] - preSum[low]));
                low = idx;
            }
            res = Math.min(res, t);
        }
        return res == Long.MAX_VALUE ? -1 : (int) (res % 1000000007);
    }

    private int searchRight(int[] packages, int target, int low) {
        int high = packages.length;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (packages[mid] <= target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}