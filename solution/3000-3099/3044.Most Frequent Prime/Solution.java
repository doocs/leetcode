class Solution {
    public int mostFrequentPrime(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                for (int a = -1; a <= 1; ++a) {
                    for (int b = -1; b <= 1; ++b) {
                        if (a == 0 && b == 0) {
                            continue;
                        }
                        int x = i + a, y = j + b, v = mat[i][j];
                        while (x >= 0 && x < m && y >= 0 && y < n) {
                            v = v * 10 + mat[x][y];
                            if (isPrime(v)) {
                                cnt.merge(v, 1, Integer::sum);
                            }
                            x += a;
                            y += b;
                        }
                    }
                }
            }
        }
        int ans = -1, mx = 0;
        for (var e : cnt.entrySet()) {
            int v = e.getKey(), x = e.getValue();
            if (mx < x || (mx == x && ans < v)) {
                mx = x;
                ans = v;
            }
        }
        return ans;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= n / i; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}