class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        counter[0] = 1;
        int ans = 0, s = 0;
        for (int& num : nums) {
            s += num;
            ans += counter[s - k];
            ++counter[s];
        }
        return ans;
    }
};