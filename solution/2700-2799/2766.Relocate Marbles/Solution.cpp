class Solution {
public:
    vector<int> relocateMarbles(vector<int>& nums, vector<int>& moveFrom, vector<int>& moveTo) {
        unordered_set<int> pos(nums.begin(), nums.end());
        for (int i = 0; i < moveFrom.size(); ++i) {
            pos.erase(moveFrom[i]);
            pos.insert(moveTo[i]);
        }
        vector<int> ans(pos.begin(), pos.end());
        sort(ans.begin(), ans.end());
        return ans;
    }
};