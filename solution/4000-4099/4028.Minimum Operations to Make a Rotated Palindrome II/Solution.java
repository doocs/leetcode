class Solution {
    static final double PI = Math.PI;

    void fft(double[] re, double[] im, boolean inv) {
        int n = re.length;

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>= 1;
            }
            j ^= bit;

            if (i < j) {
                double t = re[i];
                re[i] = re[j];
                re[j] = t;

                t = im[i];
                im[i] = im[j];
                im[j] = t;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2.0 * PI / len * (inv ? -1 : 1);
            double wr = Math.cos(ang);
            double wi = Math.sin(ang);

            int half = len >> 1;

            for (int i = 0; i < n; i += len) {
                double cr = 1.0;
                double ci = 0.0;

                for (int j = 0; j < half; j++) {
                    int x = i + j;
                    int y = x + half;

                    double tr = re[y] * cr - im[y] * ci;
                    double ti = re[y] * ci + im[y] * cr;

                    double ur = re[x];
                    double ui = im[x];

                    re[x] = ur + tr;
                    im[x] = ui + ti;
                    re[y] = ur - tr;
                    im[y] = ui - ti;

                    double nr = cr * wr - ci * wi;
                    double ni = cr * wi + ci * wr;
                    cr = nr;
                    ci = ni;
                }
            }
        }

        if (inv) {
            for (int i = 0; i < n; i++) {
                re[i] /= n;
                im[i] /= n;
            }
        }
    }

    public int minOperations(String s) {
        int n = s.length();

        int size = 1;
        while (size < 2 * n) {
            size <<= 1;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - 'a';
        }

        double[] cost = new double[26];

        for (int t = 0; t < 26; t++) {
            for (int z = 0; z < 26; z++) {
                int d = Math.min(z, 26 - z);
                cost[t] += d * Math.cos(-2.0 * PI * t * z / 26);
            }
        }

        double[] dp = new double[n];

        double[] re = new double[size];
        double[] im = new double[size];

        double[] bre = new double[size];
        double[] bim = new double[size];

        for (int t = 0; t < 14; t++) {
            double theta = 2.0 * PI * t / 26;

            for (int i = 0; i < n; i++) {
                double angle = theta * nums[i];
                re[i] = Math.cos(angle);
                im[i] = Math.sin(angle);
            }

            Arrays.fill(re, n, size, 0);
            Arrays.fill(im, n, size, 0);

            fft(re, im, false);

            for (int i = 0; i < size; i++) {
                double ar = re[i];
                double ai = im[i];

                int j = (size - i) & (size - 1);

                double br = re[j];
                double bi = -im[j];

                bre[i] = ar * br - ai * bi;
                bim[i] = ar * bi + ai * br;

                bim[i] = -bim[i];
            }

            fft(bre, bim, false);

            double mult = (t == 0 || t == 13) ? 1.0 : 2.0;
            double factor = mult * cost[t] / size;

            for (int c = 0; c < n; c++) {
                dp[c] += factor * (bre[c] + bre[c + n]);
            }
        }

        long ans = Long.MAX_VALUE;

        for (int k = 0; k < n; k++) {
            int c = (2 * k + n - 1) % n;
            long d = Math.round(dp[c] / 52.0);

            ans = Math.min(ans, k + d);
        }

        return (int) ans;
    }
}