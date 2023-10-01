class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_map<int, int> count;
        for (int num : nums) {
            ++count[num];
        }
        int ans = 0;
        for (auto& [_, c] : count) {
            if (c < 2) {
                return -1;
            }
            ans += (c + 2) / 3;
        }
        return ans;
    }
};