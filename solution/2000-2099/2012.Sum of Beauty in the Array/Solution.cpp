class Solution {
public:
    int sumOfBeauties(vector<int>& nums) {
        int n = nums.size();
        vector<int> lmx(n);
        vector<int> rmi(n, 100001);
        for (int i = 1; i < n; ++i) lmx[i] = max(lmx[i - 1], nums[i - 1]);
        for (int i = n - 2; i >= 0; --i) rmi[i] = min(rmi[i + 1], nums[i + 1]);
        int ans = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (lmx[i] < nums[i] && nums[i] < rmi[i])
                ans += 2;
            else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1])
                ans += 1;
        }
        return ans;
    }
};