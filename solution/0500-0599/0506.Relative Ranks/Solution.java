class Solution {
    public String[] findRelativeRanks(int[] score) {
        int n = score.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i1, i2) -> score[i2] - score[i1]);
        String[] ans = new String[n];
        String[] top3 = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int i = 0; i < n; ++i) {
            ans[idx[i]] = i < 3 ? top3[i] : String.valueOf(i + 1);
        }
        return ans;
    }
}