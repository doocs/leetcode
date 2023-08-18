class Solution {
public:
    int scoreOfStudents(string s, vector<int>& answers) {
        int n = s.size();
        int x = cal(s);
        int m = (n + 1) >> 1;
        unordered_set<int> f[m][m];
        for (int i = 0; i < m; ++i) {
            f[i][i] = {s[i * 2] - '0'};
        }
        for (int i = m - 1; ~i; --i) {
            for (int j = i; j < m; ++j) {
                for (int k = i; k < j; ++k) {
                    for (int l : f[i][k]) {
                        for (int r : f[k + 1][j]) {
                            char op = s[k << 1 | 1];
                            if (op == '+' && l + r <= 1000) {
                                f[i][j].insert(l + r);
                            } else if (op == '*' && l * r <= 1000) {
                                f[i][j].insert(l * r);
                            }
                        }
                    }
                }
            }
        }
        int cnt[1001]{};
        for (int t : answers) {
            ++cnt[t];
        }
        int ans = 5 * cnt[x];
        for (int i = 0; i <= 1000; ++i) {
            if (i != x && f[0][m - 1].count(i)) {
                ans += cnt[i] << 1;
            }
        }
        return ans;
    }

    int cal(string& s) {
        int res = 0;
        int pre = s[0] - '0';
        for (int i = 1; i < s.size(); i += 2) {
            int cur = s[i + 1] - '0';
            if (s[i] == '*') {
                pre *= cur;
            } else {
                res += pre;
                pre = cur;
            }
        }
        res += pre;
        return res;
    }
};