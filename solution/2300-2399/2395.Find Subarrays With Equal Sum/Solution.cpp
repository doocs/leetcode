class Solution {
public:
    bool findSubarrays(vector<int>& nums) {
        unordered_set<int> s;
        for (int i = 0; i < nums.size() - 1; ++i) {
            int v = nums[i] + nums[i + 1];
            if (s.count(v)) return true;
            s.insert(v);
        }
        return false;
    }
};