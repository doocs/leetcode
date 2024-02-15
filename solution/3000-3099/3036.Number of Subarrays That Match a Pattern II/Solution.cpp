int ps[1000001];
class Solution {
public:
    int countMatchingSubarrays(vector<int>& nums, vector<int>& pattern) {
        int N = size(pattern);
        ps[0] = -1;
        ps[1] = 0;
        for (int i = 2, p = 0; i <= N; ++i) {
            int x = pattern[i - 1];
            while (p >= 0 && pattern[p] != x) {
                p = ps[p];
            }
            ps[i] = ++p;
        }

        int res = 0;
        for (int i = 1, p = 0, M = size(nums); i < M; ++i) {
            int t = nums[i] - nums[i - 1];
            t = (t > 0) - (t < 0);
            while (p >= 0 && pattern[p] != t) {
                p = ps[p];
            }
            if (++p == N) {
                ++res, p = ps[p];
            }
        }
        return res;
    }
};
