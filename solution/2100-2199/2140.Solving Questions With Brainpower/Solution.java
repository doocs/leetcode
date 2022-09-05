class Solution {
    private long[] memo;
    private int[][] questions;

    public long mostPoints(int[][] questions) {
        this.questions = questions;
        memo = new long[questions.length];
        Arrays.fill(memo, -1);
        return dfs(0);
    }

    private long dfs(int i) {
        if (i >= questions.length) {
            return 0;
        }
        if (memo[i] != -1) {
            return memo[i];
        }
        long ans = Math.max(questions[i][0] + dfs(i + questions[i][1] + 1), dfs(i + 1));
        memo[i] = ans;
        return ans;
    }
}