class Solution {
public:
    long long countConsistent(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> stk;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.empty() && (nums[stk.back()] & x) == x) {
                stk.pop_back();
            }
            if (!stk.empty()) {
                left[i] = stk.back();
            }
            stk.push_back(i);
        }

        stk.clear();
        long long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!stk.empty() && nums[stk.back()] != x && (nums[stk.back()] & x) == x) {
                stk.pop_back();
            }
            int right = stk.empty() ? n : stk.back();
            stk.push_back(i);
            ans += 1LL * (i - left[i]) * (right - i);
        }
        return ans;
    }
};