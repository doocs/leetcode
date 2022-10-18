class Solution {
    public double averageWaitingTime(int[][] customers) {
        double tot = 0;
        int t = 0;
        for (var e : customers) {
            int a = e[0], b = e[1];
            t = Math.max(t, a) + b;
            tot += t - a;
        }
        return tot / customers.length;
    }
}