class Solution {
    public long maximumValue(int n, int s, int m) {
        if (n == 1) {
            return s;
        }
        return (long) s + (long) (n / 2) * (m - 1) + 1;
    }
}