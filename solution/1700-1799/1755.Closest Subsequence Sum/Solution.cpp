class Solution {
public:
    int minAbsDifference(vector<int>& nums, int goal) {
        int n = nums.size();
        vector<int> lsum;
        vector<int> rsum;
        dfs(nums, lsum, 0, n / 2, 0);
        dfs(nums, rsum, n / 2, n, 0);

        sort(rsum.begin(), rsum.end());
        int res = INT_MAX;

        for (int x : lsum) {
            int target = goal - x;
            int left = 0, right = rsum.size();
            while (left < right) {
                int mid = (left + right) >> 1;
                if (rsum[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if (left < rsum.size()) {
                res = min(res, abs(target - rsum[left]));
            }
            if (left > 0) {
                res = min(res, abs(target - rsum[left - 1]));
            }
        }

        return res;
    }

private:
    void dfs(vector<int>& nums, vector<int>& sum, int i, int n, int cur) {
        if (i == n) {
            sum.emplace_back(cur);
            return;
        }

        dfs(nums, sum, i + 1, n, cur);
        dfs(nums, sum, i + 1, n, cur + nums[i]);
    }
};
