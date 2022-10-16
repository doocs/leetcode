class Solution {
public:
    int countDistinctIntegers(vector<int>& nums) {
        unordered_set<int> s(nums.begin(), nums.end());
        for (int x : nums) {
            int y = 0;
            while (x) {
                y = y * 10 + x % 10;
                x /= 10;
            }
            s.insert(y);
        }
        return s.size();
    }
};