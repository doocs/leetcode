class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int ans = 0;
        int[] counter = new int[100];
        for (int[] d : dominoes) {
            int v = d[0] > d[1] ? d[0] * 10 + d[1] : d[1] * 10 + d[0];
            ans += counter[v];
            ++counter[v];
        }
        return ans;
    }
}