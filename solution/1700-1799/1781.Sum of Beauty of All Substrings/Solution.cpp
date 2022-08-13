class Solution {
public:
    int beautySum(string s) {
        int ans = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            vector<int> counter(26);
            for (int j = i; j < n; ++j) {
                ++counter[s[j] - 'a'];
                int mi = 1000;
                int mx = 0;
                for (int v : counter) {
                    if (v) {
                        mi = min(mi, v);
                        mx = max(mx, v);
                    }
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
};