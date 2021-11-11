class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> counter = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                counter.put(a + b, counter.getOrDefault(a + b, 0) + 1);
            }
        }
        int ans = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                ans += counter.getOrDefault(-(c + d), 0);
            }
        }
        return ans;
    }
}