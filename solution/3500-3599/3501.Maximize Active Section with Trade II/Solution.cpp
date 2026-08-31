class Solution {
public:
    vector<int> maxActiveSectionsAfterTrade(string s, vector<vector<int>>& queries) {
        int n = s.size();
        int active = count(s.begin(), s.end(), '1');
        if (s.find('0') == string::npos) {
            return vector<int>(queries.size(), active);
        }

        vector<pair<int, int>> zeros;
        vector<int> idx(n);
        for (int i = 0; i < n; ++i) {
            if (s[i] == '0') {
                if (i && s[i - 1] == '0') {
                    ++zeros.back().second;
                } else {
                    zeros.emplace_back(i, 1);
                }
            }
            idx[i] = (int) zeros.size() - 1;
        }

        int m = (int) zeros.size() - 1;
        int K = m ? 32 - __builtin_clz(m) : 0;
        vector<vector<int>> st(max(K, 1), vector<int>(max(m, 0)));
        for (int i = 0; i < m; ++i) {
            st[0][i] = zeros[i].second + zeros[i + 1].second;
        }
        for (int k = 1; k < K; ++k) {
            for (int i = 0; i + (1 << k) <= m; ++i) {
                st[k][i] = max(st[k - 1][i], st[k - 1][i + (1 << (k - 1))]);
            }
        }

        auto query = [&](int l, int r) {
            if (l > r || m <= 0) {
                return 0;
            }
            int k = 31 - __builtin_clz(r - l + 1);
            return max(st[k][l], st[k][r - (1 << k) + 1]);
        };

        vector<int> ans;
        ans.reserve(queries.size());
        for (auto& q : queries) {
            int L = q[0], R = q[1];
            int iL = idx[L], iR = idx[R];
            int cntL = iL < 0 ? -1 : zeros[iL].second - (L - zeros[iL].first);
            int cntR = iR < 0 ? -1 : R - zeros[iR].first + 1;
            int start = iL + 1;
            int end = iR - (s[R] == '0');
            int best = active;
            if (start < end) {
                best = max(best, active + query(start, end - 1));
            }
            if (s[L] == '0' && s[R] == '0' && iL + 1 == iR) {
                best = max(best, active + cntL + cntR);
            }
            if (s[L] == '0' && iL + 1 < iR + (s[R] == '1')) {
                best = max(best, active + cntL + zeros[iL + 1].second);
            }
            if (s[R] == '0' && iL < iR - 1) {
                best = max(best, active + cntR + zeros[iR - 1].second);
            }
            ans.push_back(best);
        }
        return ans;
    }
};
