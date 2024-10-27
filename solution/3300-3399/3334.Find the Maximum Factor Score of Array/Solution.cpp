class Solution {
public:
    long long maxScore(vector<int>& nums) {
        int n = nums.size();
        vector<long long> sufGcd(n + 1, 0);
        vector<long long> sufLcm(n + 1, 1);
        for (int i = n - 1; i >= 0; --i) {
            sufGcd[i] = gcd(sufGcd[i + 1], nums[i]);
            sufLcm[i] = lcm(sufLcm[i + 1], nums[i]);
        }

        long long ans = sufGcd[0] * sufLcm[0];
        long long preGcd = 0, preLcm = 1;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, gcd(preGcd, sufGcd[i + 1]) * lcm(preLcm, sufLcm[i + 1]));
            preGcd = gcd(preGcd, nums[i]);
            preLcm = lcm(preLcm, nums[i]);
        }
        return ans;
    }
};
