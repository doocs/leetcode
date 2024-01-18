class Solution {
public:
    vector<bool> canMakePalindromeQueries(string s, vector<vector<int>>& queries) {
        int n = s.length();
        int m = n / 2;
        string t = string(s.begin() + m, s.end());
        reverse(t.begin(), t.end());
        s = string(s.begin(), s.begin() + m);

        vector<vector<int>> pre1(m + 1, vector<int>(26));
        vector<vector<int>> pre2(m + 1, vector<int>(26));
        vector<int> diff(m + 1, 0);
        for (int i = 1; i <= m; ++i) {
            pre1[i] = pre1[i - 1];
            pre2[i] = pre2[i - 1];
            ++pre1[i][s[i - 1] - 'a'];
            ++pre2[i][t[i - 1] - 'a'];
            diff[i] = diff[i - 1] + (s[i - 1] == t[i - 1] ? 0 : 1);
        }

        vector<bool> ans(queries.size(), false);
        for (int i = 0; i < queries.size(); ++i) {
            vector<int> q = queries[i];
            int a = q[0], b = q[1];
            int c = n - 1 - q[3], d = n - 1 - q[2];
            ans[i] = (a <= c) ? check(pre1, pre2, diff, a, b, c, d) : check(pre2, pre1, diff, c, d, a, b);
        }
        return ans;
    }

private:
    bool check(const vector<vector<int>>& pre1, const vector<vector<int>>& pre2, const vector<int>& diff, int a, int b, int c, int d) {
        if (diff[a] > 0 || diff[diff.size() - 1] - diff[max(b, d) + 1] > 0) {
            return false;
        }

        if (d <= b) {
            return count(pre1, a, b) == count(pre2, a, b);
        }

        if (b < c) {
            return diff[c] - diff[b + 1] == 0 && count(pre1, a, b) == count(pre2, a, b) && count(pre1, c, d) == count(pre2, c, d);
        }

        vector<int> cnt1 = sub(count(pre1, a, b), count(pre2, a, c - 1));
        vector<int> cnt2 = sub(count(pre2, c, d), count(pre1, b + 1, d));

        return cnt1 != vector<int>() && cnt2 != vector<int>() && cnt1 == cnt2;
    }

    vector<int> count(const vector<vector<int>>& pre, int i, int j) {
        vector<int> cnt(26);
        for (int k = 0; k < 26; ++k) {
            cnt[k] = pre[j + 1][k] - pre[i][k];
        }
        return cnt;
    }

    vector<int> sub(const vector<int>& cnt1, const vector<int>& cnt2) {
        vector<int> cnt(26);
        for (int i = 0; i < 26; ++i) {
            cnt[i] = cnt1[i] - cnt2[i];
            if (cnt[i] < 0) {
                return vector<int>();
            }
        }
        return cnt;
    }
};