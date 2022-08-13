class Solution {
public:
    vector<int> f;
    vector<int> nums;
    int n;

    bool validPartition(vector<int>& nums) {
        n = nums.size();
        this->nums = nums;
        f.assign(n, -1);
        return dfs(0);
    }

    bool dfs(int i) {
        if (i == n) return true;
        if (f[i] != -1) return f[i] == 1;
        bool res = false;
        if (i < n - 1 && nums[i] == nums[i + 1]) res = res || dfs(i + 2);
        if (i < n - 2 && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2]) res = res || dfs(i + 3);
        if (i < n - 2 && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1) res = res || dfs(i + 3);
        f[i] = res ? 1 : 0;
        return res;
    }
};