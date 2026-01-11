class Solution {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            Set<Integer> st = new HashSet<>();
            int s = 0;
            for (int j = i; j < n; j++) {
                s += nums[j];
                st.add(nums[j]);
                if (st.contains(s)) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
