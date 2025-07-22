class Solution {
public:
    vector<long long> countKConstraintSubstrings(string s, int k, vector<vector<int>>& queries) {
        int cnt[2]{};
        int n = s.size();
        vector<int> d(n, n);
        long long pre[n + 1];
        pre[0] = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            cnt[s[j] - '0']++;
            while (cnt[0] > k && cnt[1] > k) {
                d[i] = j;
                cnt[s[i++] - '0']--;
            }
            pre[j + 1] = pre[j] + j - i + 1;
        }
        vector<long long> ans;
        for (const auto& q : queries) {
            int l = q[0], r = q[1];
            int p = min(r + 1, d[l]);
            long long a = (1LL + p - l) * (p - l) / 2;
            long long b = pre[r + 1] - pre[p];
            ans.push_back(a + b);
        }
        return ans;
    }
};
