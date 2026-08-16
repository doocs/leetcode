class Solution {
    public long elevatorRequests(int n, int start, int[][] requests) {
        int m = requests.length;
        long[][] f = new long[1 << m][m];

        for (int i = 0; i < (1 << m); i++) {
            for (int j = 0; j < m; j++) {
                if (((i >> j) & 1) == 1) {
                    f[i][j] = Long.MAX_VALUE;
                    int i0 = i ^ (1 << j);

                    if (i0 == 0) {
                        long d = Math.abs(start - requests[j][1]);
                        f[i][j] = Math.min(f[i][j], Math.max(d, requests[j][0]));
                    } else {
                        for (int j0 = 0; j0 < m; j0++) {
                            if (j0 != j && ((i >> j0) & 1) == 1) {
                                long d = Math.abs(requests[j0][1] - requests[j][1]);

                                f[i][j]
                                    = Math.min(f[i][j], Math.max(f[i0][j0] + d, requests[j][0]));
                            }
                        }
                    }
                }
            }
        }

        long ans = Long.MAX_VALUE;

        for (int j = 0; j < m; j++) {
            ans = Math.min(ans, f[(1 << m) - 1][j]);
        }

        return ans;
    }
}