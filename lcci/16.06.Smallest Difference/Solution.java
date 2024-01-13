class Solution {
    public int smallestDifference(int[] a, int[] b) {
        Arrays.sort(b);
        long ans = Long.MAX_VALUE;
        for (int x : a) {
            int j = search(b, x);
            if (j < b.length) {
                ans = Math.min(ans, (long) b[j] - x);
            }
            if (j > 0) {
                ans = Math.min(ans, (long) x - b[j - 1]);
            }
        }
        return (int) ans;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}