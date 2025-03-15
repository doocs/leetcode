class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        unordered_set<int> ans, vis;
        for (int x : nums) {
            if (vis.count(x - k)) {
                ans.insert(x - k);
            }
            if (vis.count(x + k)) {
                ans.insert(x);
            }
            vis.insert(x);
        }
        return ans.size();
    }
};
