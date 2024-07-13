class Solution {
    public int countCompleteDayPairs(int[] hours) {
        int[] cnt = new int[24];
        int ans = 0;
        for (int x : hours) {
            ans += cnt[(24 - x % 24) % 24];
            ++cnt[x % 24];
        }
        return ans;
    }
}