class Solution {
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int start = 0, end = piles.length - 1, ans = 0;
        while (start < end) {
            ans += piles[end - 1];
            start++;
            end -= 2;
        }
        return ans;
    }
}