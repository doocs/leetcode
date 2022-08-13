class Solution {
public:
    int findPairs(vector<int>& nums, int k) {
        unordered_set<int> vis;
        unordered_set<int> ans;
        for (int& v : nums) {
            if (vis.count(v - k)) ans.insert(v - k);
            if (vis.count(v + k)) ans.insert(v);
            vis.insert(v);
        }
        return ans.size();
    }
};