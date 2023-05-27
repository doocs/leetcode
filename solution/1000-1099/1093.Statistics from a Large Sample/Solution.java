class Solution {
    private int[] count;

    public double[] sampleStats(int[] count) {
        this.count = count;
        int mi = 1 << 30, mx = -1;
        long s = 0;
        int cnt = 0;
        int mode = 0;
        for (int k = 0; k < count.length; ++k) {
            if (count[k] > 0) {
                mi = Math.min(mi, k);
                mx = Math.max(mx, k);
                s += 1L * k * count[k];
                cnt += count[k];
                if (count[k] > count[mode]) {
                    mode = k;
                }
            }
        }
        double median
            = cnt % 2 == 1 ? find(cnt / 2 + 1) : (find(cnt / 2) + find(cnt / 2 + 1)) / 2.0;
        return new double[] {mi, mx, s * 1.0 / cnt, median, mode};
    }

    private int find(int i) {
        for (int k = 0, t = 0;; ++k) {
            t += count[k];
            if (t >= i) {
                return k;
            }
        }
    }
}