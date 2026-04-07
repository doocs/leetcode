class Solution {
public:
    int countSubstrings(string s) {
        string t = "^#";
        for (char c : s) {
            t += c;
            t += '#';
        }
        t += "$";

        int n = t.size();
        vector<int> p(n, 0);
        int pos = 0, maxRight = 0;
        int ans = 0;

        for (int i = 1; i < n - 1; ++i) {
            if (maxRight > i) {
                p[i] = min(maxRight - i, p[2 * pos - i]);
            } else {
                p[i] = 1;
            }

            while (t[i - p[i]] == t[i + p[i]]) {
                ++p[i];
            }

            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                pos = i;
            }

            ans += p[i] / 2;
        }

        return ans;
    }
};
