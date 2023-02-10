class Solution {
public:
    int numPairsDivisibleBy60(vector<int>& time) {
        int cnt[501]{};
        int ans = 0;
        for (int& t : time) {
            int s = 60;
            for (int i = 0; i < 17; ++i) {
                if (s - t >= 0 && s - t < 501) {
                    ans += cnt[s - t];
                }
                s += 60;
            }
            cnt[t]++;
        }
        return ans;
    }
};