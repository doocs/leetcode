class Solution {
    public long[] multiply(int[] poly1, int[] poly2) {
        if (poly1 == null || poly2 == null || poly1.length == 0 || poly2.length == 0) {
            return new long[0];
        }

        int m = poly1.length + poly2.length - 1;
        int n = 1;
        while (n < m) n <<= 1;

        Complex[] fa = new Complex[n];
        Complex[] fb = new Complex[n];
        for (int i = 0; i < n; i++) {
            fa[i] = new Complex(i < poly1.length ? poly1[i] : 0, 0);
            fb[i] = new Complex(i < poly2.length ? poly2[i] : 0, 0);
        }

        fft(fa, false);
        fft(fb, false);

        for (int i = 0; i < n; i++) {
            fa[i] = fa[i].mul(fb[i]);
        }

        fft(fa, true);

        long[] res = new long[m];
        for (int i = 0; i < m; i++) {
            res[i] = Math.round(fa[i].re);
        }
        return res;
    }

    private static void fft(Complex[] a, boolean invert) {
        int n = a.length;

        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >>> 1;
            while ((j & bit) != 0) {
                j ^= bit;
                bit >>>= 1;
            }
            j ^= bit;
            if (i < j) {
                Complex tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }

        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2 * Math.PI / len * (invert ? -1 : 1);
            Complex wlen = new Complex(Math.cos(ang), Math.sin(ang));

            for (int i = 0; i < n; i += len) {
                Complex w = new Complex(1, 0);
                int half = len >>> 1;
                for (int j = 0; j < half; j++) {
                    Complex u = a[i + j];
                    Complex v = a[i + j + half].mul(w);
                    a[i + j] = u.add(v);
                    a[i + j + half] = u.sub(v);
                    w = w.mul(wlen);
                }
            }
        }

        if (invert) {
            for (int i = 0; i < n; i++) {
                a[i].re /= n;
                a[i].im /= n;
            }
        }
    }

    private static final class Complex {
        double re, im;
        Complex(double re, double im) {
            this.re = re;
            this.im = im;
        }
        Complex add(Complex o) {
            return new Complex(re + o.re, im + o.im);
        }
        Complex sub(Complex o) {
            return new Complex(re - o.re, im - o.im);
        }
        Complex mul(Complex o) {
            return new Complex(re * o.re - im * o.im, re * o.im + im * o.re);
        }
    }
}
