class Solution {
public:
    long long zeroFilledSubarray(vector<int>& nums) {
        long long ans = 0;
        int cnt = 0;
        for (int& v : nums) {
            cnt = v ? 0 : cnt + 1;
            ans += cnt;
        }
        return ans;
    }
};