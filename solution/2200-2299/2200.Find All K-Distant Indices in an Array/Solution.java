class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n = nums.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 0; i < n; ++i) {
            while (j < i - k || (j < n && nums[j] != key)) {
                ++j;
            }
            if (j < n && j <= i + k) {
                ans.add(i);
            }
        }
        return ans;
    }
}