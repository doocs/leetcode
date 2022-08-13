class Solution {
public:
    int subarraysDivByK(vector<int>& nums, int k) {
        unordered_map<int, int> counter;
        counter[0] = 1;
        int s = 0, ans = 0;
        for (int& num : nums) {
            s += num;
            int t = (s % k + k) % k;
            ans += counter[t];
            ++counter[t];
        }
        return ans;
    }
};