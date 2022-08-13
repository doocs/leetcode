class Solution {
public:
    bool containsDuplicate(vector<int>& nums) {
        unordered_set<int> s;
        for (int e : nums) {
            if (s.count(e)) return true;
            s.insert(e);
        }
        return false;
    }
};