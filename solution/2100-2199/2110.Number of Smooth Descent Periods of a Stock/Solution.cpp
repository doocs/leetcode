class Solution {
public:
    long long getDescentPeriods(vector<int>& prices) {
        long long ans = 0;
        for (int i = 0, n = prices.size(); i < n;) {
            int j = i;
            for (; j + 1 < n && prices[j] - prices[j + 1] == 1; ++j)
                ;
            int t = j - i + 1;
            ans += (long long)t * (t + 1) / 2;
            i = j + 1;
        }
        return ans;
    }
};