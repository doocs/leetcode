class Solution {
public:
    int minArrivalsToDiscard(vector<int>& arrivals, int w, int m) {
        unordered_map<int, int> cnt;
        int n = arrivals.size();
        vector<int> marked(n, 0);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int x = arrivals[i];
            if (i >= w) {
                cnt[arrivals[i - w]] -= marked[i - w];
            }
            if (cnt[x] >= m) {
                ans++;
            } else {
                marked[i] = 1;
                cnt[x] += 1;
            }
        }
        return ans;
    }
};
