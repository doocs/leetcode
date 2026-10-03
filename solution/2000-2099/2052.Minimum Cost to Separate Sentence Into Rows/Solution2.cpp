class Solution {
public:
    int minimumCost(string sentence, int k) {
        istringstream iss(sentence);
        vector<int> s = {0};
        string w;
        while (iss >> w) {
            s.push_back(s.back() + (int) w.size());
        }
        int n = s.size() - 1;
        vector<int> f(n);
        for (int i = n - 1; i >= 0; --i) {
            if (s[n] - s[i] + n - i - 1 <= k) {
                continue;
            }
            int ans = INT_MAX;
            for (int j = i + 1; j < n && s[j] - s[i] + j - i - 1 <= k; ++j) {
                int m = s[j] - s[i] + j - i - 1;
                ans = min(ans, f[j] + (k - m) * (k - m));
            }
            f[i] = ans;
        }
        return f[0];
    }
};
