class Solution {
public:
    bool validPartition(vector<int>& nums) {
        n = nums.size();
        this->nums = nums;
        f.assign(n, -1);
        return dfs(0);
    }

private:
    int n;
    vector<int> f;
    vector<int> nums;

    bool dfs(int i) {
        if (i >= n) {
            return true;
        }
        if (f[i] != -1) {
            return f[i] == 1;
        }
        bool a = i + 1 < n && nums[i] == nums[i + 1];
        bool b = i + 2 < n && nums[i] == nums[i + 1] && nums[i + 1] == nums[i + 2];
        bool c = i + 2 < n && nums[i + 1] - nums[i] == 1 && nums[i + 2] - nums[i + 1] == 1;
        f[i] = ((a && dfs(i + 2)) || ((b || c) && dfs(i + 3))) ? 1 : 0;
        return f[i] == 1;
    }
};