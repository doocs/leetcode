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
            if (cnt) {
                if (i == 31) {
                    ans = INT_MIN + ans;
                } else {
                    ans |= cnt << i;
                }
            }
        }
        return ans;
    }
};
