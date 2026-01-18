class Solution {
public:
    int alternatingXOR(vector<int>& nums, int target1, int target2) {
        const int MOD = 1e9 + 7;
        unordered_map<int, int> cnt1, cnt2;
        cnt2[0] = 1;

        int pre = 0, ans = 0;
        for (int x : nums) {
            pre ^= x;
            int a = cnt2[pre ^ target1];
            int b = cnt1[pre ^ target2];
            ans = (a + b) % MOD;
            cnt1[pre] = (cnt1[pre] + a) % MOD;
            cnt2[pre] = (cnt2[pre] + b) % MOD;
        }

        return ans;
    }
};
