class Solution {
    public long[] countStableSubarrays(int[] nums, int[][] queries) {
        List<Integer> seg = new ArrayList<>();
        List<Long> s = new ArrayList<>();
        s.add(0L);

        int l = 0;
        int n = nums.length;
        for (int r = 0; r < n; r++) {
            if (r == n - 1 || nums[r] > nums[r + 1]) {
                seg.add(l);
                int k = r - l + 1;
                s.add(s.getLast() + (long) k * (k + 1) / 2);
                l = r + 1;
            }
        }

        long[] ans = new long[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int left = queries[q][0];
            int right = queries[q][1];

            int i = upperBound(seg, left);
            int j = upperBound(seg, right) - 1;

            if (i > j) {
                int k = right - left + 1;
                ans[q] = (long) k * (k + 1) / 2;
            } else {
                int a = seg.get(i) - left;
                int b = right - seg.get(j) + 1;
                ans[q] = (long) a * (a + 1) / 2 + s.get(j) - s.get(i) + (long) b * (b + 1) / 2;
            }
        }
        return ans;
    }

    private int upperBound(List<Integer> list, int target) {
        int l = 0, r = list.size();
        while (l < r) {
            int mid = (l + r) >> 1;
            if (list.get(mid) > target) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
