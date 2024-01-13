class Solution {
    public int runeReserve(int[] runes) {
        Arrays.sort(runes);
        int ans = 0;
        for (int i = 0, j = 0; j < runes.length; ++j) {
            if (j > 0 && runes[j] - runes[j - 1] > 1) {
                i = j;
            } else {
                ans = Math.max(ans, j - i + 1);
            }
        }
        return ans;
    }
}