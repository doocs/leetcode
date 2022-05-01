class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        Set<String> s = new HashSet<>();
        for (int i = 0, n = nums.length; i < n; ++i) {
            int cnt = 0;
            String t = "";
            for (int j = i; j < n; ++j) {
                if (nums[j] % p == 0) {
                    ++cnt;
                }
                if (cnt > k) {
                    break;
                }
                t += nums[j] + ",";
                s.add(t);
            }
        }
        return s.size();
    }
}