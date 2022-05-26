class Solution {
public:
    int findLHS(vector<int>& nums) {
        unordered_map<int, int> counter;
        for (int num : nums) {
            ++counter[num];
        }
        int ans = 0;
        for (int num : nums) {
            if (counter.count(num + 1)) {
                ans = max(ans, counter[num] + counter[num + 1]);
            }
        }
        return ans;
    }
};