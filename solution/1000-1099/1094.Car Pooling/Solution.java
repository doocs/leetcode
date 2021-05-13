class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] delta = new int[1001];
        for (int[] trip : trips) {
            int num = trip[0], start = trip[1], end = trip[2];
            delta[start] += num;
            delta[end] -= num;
        }
        int cur = 0;
        for (int num : delta) {
            cur += num;
            if (cur > capacity) {
                return false;
            }
        }
        return true;
    }
}