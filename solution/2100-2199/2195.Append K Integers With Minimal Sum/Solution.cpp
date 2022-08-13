class Solution {
public:
    long long minimalKSum(vector<int>& nums, int k) {
        nums.push_back(0);
        nums.push_back(2e9);
        sort(nums.begin(), nums.end());
        long long ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            int a = nums[i - 1], b = nums[i];
            int n = min(k, b - a - 1);
            if (n <= 0) continue;
            k -= n;
            ans += 1ll * (a + 1 + a + n) * n / 2;
            if (k == 0) break;
        }
        return ans;
    }
};