class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int ans = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (m.containsKey(sum)) {
                ans = Math.max(ans, i - m.get(sum));
            } else {
                m.put(sum, i);
            }
        }
        return ans;
    }
}
