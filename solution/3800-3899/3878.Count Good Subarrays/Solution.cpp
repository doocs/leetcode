class Solution {
public:
    long long countGoodSubarrays(vector<int>& nums) {
        int n = nums.size();

        vector<int> l(n, -1);
        vector<int> stk;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.empty() && nums[stk.back()] < x && (nums[stk.back()] | x) == x) {
                stk.pop_back();
            }
            l[i] = stk.empty() ? -1 : stk.back();
            stk.push_back(i);
        }

        vector<int> r(n, n);
        stk.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.empty() && (nums[stk.back()] | nums[i]) == nums[i]) {
                stk.pop_back();
            }
            r[i] = stk.empty() ? n : stk.back();
            stk.push_back(i);
        }

        long long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += 1LL * (i - l[i]) * (r[i] - i);
        }
        return ans;
    }
};
