class Solution {
    public double[] sampleStats(int[] count) {
        int n = count.length;
        int mode = 0, modeMax = 0;
        int min = -1, max = -1;
        double avg = 0;
        int cnt = 0;
        for (int i = 0; i < n; ++i) {
            if (count[i] > modeMax) {
                modeMax = count[i];
                mode = i;
            }
            if (count[i] != 0) {
                cnt += count[i];
                avg += count[i] * i;
                if (min == -1) min = i;
                max = i;
            }
        }
        avg /= cnt;
        // 求中位数
        double mid = 0;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += count[i];
            if (sum << 1 > cnt) {
                mid = i;
                break;
            } else if (sum << 1 == cnt) {
                for (int j = i + 1; j < n; ++j) {
                    if (count[j] != 0) {
                        mid = (i + j) / 2.0;
                        break;
                    }
                }
                break;
            }
        }
        return new double[] {min, max, avg, mid, mode};
    }
}
