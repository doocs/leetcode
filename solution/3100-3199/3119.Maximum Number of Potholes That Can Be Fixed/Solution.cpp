class Solution {
public:
    int maxPotholes(string road, int budget) {
        road.push_back('.');
        int n = road.size();
        vector<int> cnt(n);
        int k = 0;
        for (char& c : road) {
            if (c == 'x') {
                ++k;
            } else if (k) {
                ++cnt[k];
                k = 0;
            }
        }
        int ans = 0;
        for (k = n - 1; k; --k) {
            int t = min(budget / (k + 1), cnt[k]);
            ans += t * k;
            budget -= t * (k + 1);
            cnt[k - 1] += cnt[k] - t;
        }
        return ans;
    }
};