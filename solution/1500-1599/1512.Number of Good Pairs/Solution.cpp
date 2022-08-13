class Solution {
public:
    int numIdenticalPairs(vector<int>& nums) {
        unordered_map<int, int> counter;
        for (int num : nums) {
            ++counter[num];
        }
        int res = 0;
        for (auto& [num, n] : counter) {
            res += n * (n - 1);
        }
        return res >> 1;
    }
};