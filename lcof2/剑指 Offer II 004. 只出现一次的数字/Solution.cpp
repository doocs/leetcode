class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int cnt = 0;
            for (int num : nums) {
                cnt += ((num >> i) & 1);
            }
            cnt %= 3;
            ans |= cnt << i;
        }
        return ans;
    }
};