class Solution {
    public long maximumTripletValue(int[] nums) {
        long max, maxDiff, ans;
        max = 0;
        maxDiff = 0;
        ans = 0;
        for (int num : nums) {
            ans = Math.max(ans, num * maxDiff);
            max = Math.max(max, num);
            maxDiff = Math.max(maxDiff, max - num);
        }
        return ans;
    }
}