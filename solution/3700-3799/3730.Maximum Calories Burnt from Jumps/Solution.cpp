class Solution {
public:
    long long maxCaloriesBurnt(vector<int>& heights) {
        ranges::sort(heights);
        long long ans = 0;
        int pre = 0;
        int r = heights.size() - 1;
        for (int l = 0; l < r; ++l, --r) {
            ans += 1LL * (heights[r] - pre) * (heights[r] - pre);
            ans += 1LL * (heights[l] - heights[r]) * (heights[l] - heights[r]);
            pre = heights[l];
        }
        ans += 1LL * (heights[r] - pre) * (heights[r] - pre);
        return ans;
    }
};
