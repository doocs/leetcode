class Solution {
public:
    long long maxProduct(string s) {
        long long res = 0, l = 0, n = s.size();
        vector<int> m(n), r(n);

        for (int i = 0, l = 0, r = -1; i < n; ++i) {
            int k = (i > r) ? 1 : min(m[l + r - i], r - i + 1);
            while (0 <= i - k && i + k < n && s[i - k] == s[i + k])
                k++;
            m[i] = k--;
            if (i + k > r) {
                l = i - k;
                r = i + k;
            }
        }

        queue<array<int, 2>> q, q1;

        for (int i = n - 1; i >= 0; --i) {
            while (!q.empty() && q.front()[0] - q.front()[1] > i - 1)
                q.pop();
            r[i] = 1 + (q.empty() ? 0 : (q.front()[0] - i) * 2);
            q.push({i, m[i]});
        }

        for (int i = 0; i < n - 1; i++) {
            while (!q1.empty() && q1.front()[0] + q1.front()[1] < i + 1)
                q1.pop();
            l = max(l, 1ll + (q1.empty() ? 0 : (i - q1.front()[0]) * 2));
            res = max(res, l * r[i + 1]);
            q1.push({i, m[i]});
        }

        return res;
    }
};
