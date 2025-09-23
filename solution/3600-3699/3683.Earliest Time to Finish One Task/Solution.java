class Solution {
    public int earliestTime(int[][] tasks) {
        int ans = 200;
        for (var task : tasks) {
            ans = Math.min(ans, task[0] + task[1]);
        }
        return ans;
    }
}
