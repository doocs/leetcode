class Solution {
public:
    int minOperations(vector<int>& nums, int target) {
        long long s = 0;
        int cnt[32]{};
        for (int x : nums) {
            s += x;
            for (int i = 0; i < 32; ++i) {
                if (x >> i & 1) {
                    ++cnt[i];
                }
            }
        }
        if (s < target) {
            return -1;
        }
        int i = 0, j = 0;
        int ans = 0;
        while (1) {
            while (i < 32 && (target >> i & 1) == 0) {
                ++i;
            }
            if (i == 32) {
                return ans;
            }
            while (j < i) {
                cnt[j + 1] += cnt[j] / 2;
                cnt[j] %= 2;
                ++j;
            }
            while (cnt[j] == 0) {
                cnt[j] = 1;
                ++j;
            }
            ans += j - i;
            --cnt[j];
            j = i;
            ++i;
        }
    }
};