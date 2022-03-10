class Solution {
    public int numberOfArrays(int[] differences, int lower, int upper) {
        long num = 0, mi = 0, mx = 0;
        for (int d : differences) {
            num += d;
            mi = Math.min(mi, num);
            mx = Math.max(mx, num);
        }
        return Math.max(0, (int) (upper - lower - (mx - mi) + 1));
    }
}