class Solution {
    public int minimumSum(int[] nums1, int[] nums2) {
        int n = nums1.length;
        final int inf = 1 << 30;
        Map<Integer, Integer> d = new HashMap<>();
        for (int i = 0; i < n; i++) {
            d.putIfAbsent(nums2[i], i);
        }
        int ans = inf;
        for (int i = 0; i < n; i++) {
            if (d.containsKey(nums1[i])) {
                ans = Math.min(ans, i + d.get(nums1[i]));
            }
        }
        return ans == inf ? -1 : ans;
    }
}
