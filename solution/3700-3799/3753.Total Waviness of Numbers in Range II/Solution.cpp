class Solution {
public:
    long long totalWaviness(long long num1, long long num2) {
        return calc(num2) - calc(num1 - 1);
    }

private:
    string s;
    long long fCnt[20][11][11][2];
    long long fWav[20][11][11][2];
    bool vis[20][11][11][2];

    long long calc(long long x) {
        if (x < 0) {
            return 0;
        }
        s = to_string(x);
        memset(vis, 0, sizeof(vis));
        return dfs(0, 10, 10, 0, true).second;
    }

    pair<long long, long long> dfs(int pos, int prev2, int prev1, int started, bool limit) {
        if (pos == s.size()) {
            return {started, 0};
        }
        if (!limit && vis[pos][prev2][prev1][started]) {
            return {fCnt[pos][prev2][prev1][started], fWav[pos][prev2][prev1][started]};
        }
        int up = limit ? s[pos] - '0' : 9;
        long long c = 0, w = 0;
        for (int d = 0; d <= up; ++d) {
            bool nlimit = limit && d == up;
            int ns, np2, np1, add = 0;
            if (started == 0) {
                if (d == 0) {
                    ns = 0;
                    np2 = 10;
                    np1 = 10;
                } else {
                    ns = 1;
                    np2 = 10;
                    np1 = d;
                }
            } else {
                ns = 1;
                np2 = prev1;
                np1 = d;
                if (prev2 != 10 && ((prev1 > prev2 && prev1 > d) || (prev1 < prev2 && prev1 < d))) {
                    add = 1;
                }
            }
            auto [tc, tw] = dfs(pos + 1, np2, np1, ns, nlimit);
            c += tc;
            w += tw + tc * add;
        }
        if (!limit) {
            vis[pos][prev2][prev1][started] = true;
            fCnt[pos][prev2][prev1][started] = c;
            fWav[pos][prev2][prev1][started] = w;
        }
        return {c, w};
    }
};
