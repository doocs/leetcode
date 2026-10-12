class Solution {
public:
    vector<int> maxProductPair(vector<int>& nums, int target) {
        unordered_map<int, int> pos;
        int mx = INT_MIN;
        vector<int> ans = {-1, -1};
        for (int i = 0; i < nums.size(); ++i) {
            int x = nums[i], y = target - x;
            if (x != y && x * y > mx && pos.contains(y)) {
                int j = pos[y];
                mx = x * y;
                ans = x > y ? vector<int>{i, j} : vector<int>{j, i};
            }
            pos[x] = i;
        }
        return ans;
    }
};