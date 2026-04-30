class Solution {
    private int side;
    private long[] nums;
    private int k;

    public int maxDistance(int side, int[][] points, int k) {
        this.side = side;
        this.k = k;
        int n = points.length;
        this.nums = new long[n];
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (x == 0) {
                nums[i] = (long) y;
            } else if (y == side) {
                nums[i] = (long) side + x;
            } else if (x == side) {
                nums[i] = (long) side * 3 - y;
            } else {
                nums[i] = (long) side * 4 - x;
            }
        }
        Arrays.sort(nums);

        int l = 1, r = side;
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int lo) {
        long total = (long) side * 4;
        for (int i = 0; i < nums.length; i++) {
            long start = nums[i];
            long end = start + total - lo;
            long cur = start;
            boolean ok = true;
            for (int j = 0; j < k - 1; j++) {
                long target = cur + lo;
                int idx = lowerBound(nums, target);
                if (idx == nums.length || nums[idx] > end) {
                    ok = false;
                    break;
                }
                cur = nums[idx];
            }
            if (ok) {
                return true;
            }
        }
        return false;
    }

    private int lowerBound(long[] arr, long target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
