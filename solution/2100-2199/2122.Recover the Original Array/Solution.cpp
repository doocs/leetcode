class Solution {
public:
    vector<int> recoverArray(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 1, n = nums.size(); i < n; ++i) {
            int d = nums[i] - nums[0];
            if (d == 0 || d % 2 == 1) continue;
            vector<bool> vis(n);
            vis[i] = true;
            vector<int> ans;
            ans.push_back((nums[0] + nums[i]) >> 1);
            for (int l = 1, r = i + 1; r < n; ++l, ++r) {
                while (l < n && vis[l]) ++l;
                while (r < n && nums[r] - nums[l] < d) ++r;
                if (r == n || nums[r] - nums[l] > d) break;
                vis[r] = true;
                ans.push_back((nums[l] + nums[r]) >> 1);
            }
            if (ans.size() == (n >> 1)) return ans;
        }
        return {};
    }
};