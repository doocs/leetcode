public class Solution {
    public long MinNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        long l = 1, r = (long)1e16;

        bool Check(long t) {
            long h = 0;
            foreach (int wt in workerTimes) {
                long val = (long)(Math.Sqrt(t * 2.0 / wt + 0.25) - 0.5);
                h += val;
                if (h >= mountainHeight) {
                    return true;
                }
            }
            return h >= mountainHeight;
        }

        while (l < r) {
            long mid = (l + r) >> 1;
            if (Check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
