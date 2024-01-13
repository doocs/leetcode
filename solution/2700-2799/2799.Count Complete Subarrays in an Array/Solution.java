class Solution {
    public int countCompleteSubarrays(int[] nums) {
        Set<Integer> s = new HashSet<>();
        for (int x : nums) {
            s.add(x);
        }
        int cnt = s.size();
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            s.clear();
            for (int j = i; j < n; ++j) {
                s.add(nums[j]);
                if (s.size() == cnt) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}