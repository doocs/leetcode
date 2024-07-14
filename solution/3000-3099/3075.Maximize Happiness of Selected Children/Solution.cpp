class Solution {
public:
    long long maximumHappinessSum(vector<int>& happiness, int k) {
        sort(happiness.rbegin(), happiness.rend());
        long long ans = 0;
        for (int i = 0, n = happiness.size(); i < k; ++i) {
            int x = happiness[i] - i;
            ans += max(x, 0);
        }
        return ans;
    }
};