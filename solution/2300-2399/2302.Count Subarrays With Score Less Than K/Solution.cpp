using ll = long long;

class Solution {
public:
    long long countSubarrays(vector<int>& nums, long long k) {
        int n = nums.size();
        vector<ll> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + nums[i];
        ll ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] >= k) continue;
            int left = 1, right = i;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if ((s[i] - s[i - mid]) * mid < k)
                    left = mid;
                else
                    right = mid - 1;
            }
            ans += left;
        }
        return ans;
    }
};