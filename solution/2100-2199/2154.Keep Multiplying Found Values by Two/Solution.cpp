class Solution {
public:
    int findFinalValue(vector<int>& nums, int original) {
        unordered_set<int> s;
        for (int num : nums) s.insert(num);
        while (s.count(original)) original <<= 1;
        return original;
    }
};