class Solution {
public:
    int minSessions(vector<int>& tasks, int sessionTime) {
        int n = tasks.size();
        bool ok[1 << n];
        memset(ok, false, sizeof(ok));
        for (int i = 1; i < 1 << n; ++i) {
            int t = 0;
            for (int j = 0; j < n; ++j) {
                if (i >> j & 1) {
                    t += tasks[j];
                }
            }
            ok[i] = t <= sessionTime;
        }
        int f[1 << n];
        memset(f, 0x3f, sizeof(f));
        f[0] = 0;
        for (int i = 1; i < 1 << n; ++i) {
            for (int j = i; j; j = (j - 1) & i) {
                if (ok[j]) {
                    f[i] = min(f[i], f[i ^ j] + 1);
                }
            }
        }
        return f[(1 << n) - 1];
    }
};