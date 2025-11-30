public class Solution {
    public long MaxRunTime(int n, int[] batteries) {
        long l = 0, r = 0;
        foreach (int x in batteries) {
            r += x;
        }

        while (l < r) {
            long mid = (l + r + 1) >> 1;
            long s = 0;

            foreach (int x in batteries) {
                s += Math.Min(mid, (long)x);
            }

            if (s >= (long)n * mid) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }

        return l;
    }
}
