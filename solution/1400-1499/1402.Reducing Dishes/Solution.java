class Solution {
    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int ans = 0, presum = 0;
        for (int i = satisfaction.length - 1; i >= 0; --i) {
            presum += satisfaction[i];
            if (presum > 0) {
                ans += presum;
            } else {
                break;
            }
        }
        return ans;
    }
}