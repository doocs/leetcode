class Solution {
    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, (a, b) -> a[0] - b[0] - a[1] + b[1]);
        int ans = 0, t = 0;
        for (var e : tasks) {
            if (t < e[1]) {
                ans += e[1] - t;
                t = e[1];
            }
            t -= e[0];
        }
        return ans;
    }
}