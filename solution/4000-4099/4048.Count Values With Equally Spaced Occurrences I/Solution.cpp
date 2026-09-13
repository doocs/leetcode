class Solution {
public:
    int countSpecialIntegers(vector<int>& nums) {
        unordered_map<int, vector<int>> g;
        for (int i = 0; i < nums.size(); i++) {
            g[nums[i]].push_back(i);
        }

        int ans = 0;
        for (auto& [x, pos] : g) {
            if (pos.size() == 3 && pos[0] + pos[2] == pos[1] * 2) {
                ans++;
            }
        }
        return ans;
    }
};