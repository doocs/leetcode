class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
        long long ans = 0;
        int n = prices.size();
        for (int i = 0, j = 0; i < n; i = j) {
            j = i + 1;
            while (j < n && prices[j - 1] - prices[j] == 1) {
                ++j;
            }
            int cnt = j - i;
            ans += (1LL + cnt) * cnt / 2;
        }
        return ans;
    }
};