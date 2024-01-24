class Solution {
    public long[] countOfPairs(int n, int x, int y) {
        --x;
        --y;
        if (x > y) {
            int temp = x;
            x = y;
            y = temp;
        }
        long[] diff = new long[n];
        for (int i = 0; i < n; ++i) {
            diff[0] += 1 + 1;
            ++diff[Math.min(Math.abs(i - x), Math.abs(i - y) + 1)];
            ++diff[Math.min(Math.abs(i - y), Math.abs(i - x) + 1)];
            --diff[Math.min(Math.abs(i - 0), Math.abs(i - y) + 1 + Math.abs(x - 0))];
            --diff[Math.min(Math.abs(i - (n - 1)),
                            Math.abs(i - x) + 1 + Math.abs(y - (n - 1)))];
            --diff[Math.max(x - i, 0) + Math.max(i - y, 0) + ((y - x) + 0) / 2];
            --diff[Math.max(x - i, 0) + Math.max(i - y, 0) + ((y - x) + 1) / 2];
        }
        for (int i = 0; i + 1 < n; ++i) {
            diff[i + 1] += diff[i];
        }
        return diff;
    }
}
