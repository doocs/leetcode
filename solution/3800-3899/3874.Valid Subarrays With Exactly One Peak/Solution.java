class Solution {
    public long validSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks.add(i);
            }
        }

        long ans = 0;
        for (int j = 0; j < peaks.size(); j++) {
            int p = peaks.get(j);

            int leftMin = Math.max(p - k, 0);
            if (j > 0) {
                leftMin = Math.max(leftMin, peaks.get(j - 1) + 1);
            }

            int rightMax = Math.min(p + k, n - 1);
            if (j < peaks.size() - 1) {
                rightMax = Math.min(rightMax, peaks.get(j + 1) - 1);
            }

            ans += (long) (p - leftMin + 1) * (rightMax - p + 1);
        }

        return ans;
    }
}
