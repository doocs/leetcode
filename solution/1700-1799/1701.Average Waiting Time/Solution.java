class Solution {
    public double averageWaitingTime(int[][] customers) {
        int f = 0;
        double totalWaitingTime = 0;
        for (int[] customer : customers) {
            f = Math.max(f, customer[0]) + customer[1];
            totalWaitingTime += (f - customer[0]);
        }
        return totalWaitingTime / customers.length;
    }
}