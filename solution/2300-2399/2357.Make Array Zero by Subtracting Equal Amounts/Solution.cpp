class Solution {
public:
    int minimumOperations(vector<int>& nums) {
        unordered_set<int> s;
        for (int v : nums)
            if (v) s.insert(v);
        return s.size();
    }
};