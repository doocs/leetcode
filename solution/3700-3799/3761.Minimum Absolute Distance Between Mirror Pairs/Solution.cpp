class Solution {
public:
    int minMirrorPairDistance(vector<int>& nums) {
        int n = nums.size();
        int ans = n + 1;
        unordered_map<int, int> pos;
        auto reverse = [](int x) {
            int y = 0;
            for (; x > 0; x /= 10) {
                y = y * 10 + x % 10;
            }
            return y;
        };
        for (int i = 0; i < n; ++i) {
            if (pos.contains(nums[i])) {
                ans = min(ans, i - pos[nums[i]]);
            }
            pos[reverse(nums[i])] = i;
        }
        return ans > n ? -1 : ans;
    }
};
