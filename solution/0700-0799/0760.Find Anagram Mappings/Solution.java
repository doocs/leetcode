class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, Integer> d = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            d.put(nums2[i], i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = d.get(nums1[i]);
        }
        return ans;
    }
}
