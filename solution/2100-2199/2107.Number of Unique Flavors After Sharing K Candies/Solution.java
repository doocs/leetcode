class Solution {
    public int shareCandies(int[] candies, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int n = candies.length;
        for (int i = k; i < n; ++i) {
            cnt.merge(candies[i], 1, Integer::sum);
        }
        int ans = cnt.size();
        for (int i = k; i < candies.length; ++i) {
            if (cnt.merge(candies[i], -1, Integer::sum) == 0) {
                cnt.remove(candies[i]);
            }
            cnt.merge(candies[i - k], 1, Integer::sum);
            ans = Math.max(ans, cnt.size());
        }
        return ans;
    }
}