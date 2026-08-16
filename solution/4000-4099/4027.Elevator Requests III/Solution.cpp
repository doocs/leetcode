class Solution {
public:
    long long elevatorRequests(int n, int start, vector<vector<int>>& requests) {
        int m = requests.size();

        vector<vector<long long>> f(1 << m, vector<long long>(m, 0));

        for (int i = 0; i < (1 << m); i++) {
            for (int j = 0; j < m; j++) {
                if ((i >> j) & 1) {
                    f[i][j] = LLONG_MAX;
                    int i0 = i ^ (1 << j);

                    if (i0 == 0) {
                        long long d = abs(start - requests[j][1]);

                        f[i][j] = min(
                            f[i][j],
                            max(d, (long long) requests[j][0]));
                    } else {
                        for (int j0 = 0; j0 < m; j0++) {
                            if (j0 != j && ((i >> j0) & 1)) {
                                long long d = abs(
                                    requests[j0][1] - requests[j][1]);

                                f[i][j] = min(
                                    f[i][j],
                                    max(
                                        f[i0][j0] + d,
                                        (long long) requests[j][0]));
                            }
                        }
                    }
                }
            }
        }

        long long ans = LLONG_MAX;
        for (int j = 0; j < m; j++) {
            ans = min(ans, f[(1 << m) - 1][j]);
        }

        return ans;
    }
};