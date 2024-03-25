class Solution {
public:
    int earliestSecondToMarkIndices(vector<int>& nums, vector<int>& changeIndices) {
        int n = nums.size();
        int last[n + 1];
        auto check = [&](int t) {
            memset(last, 0, sizeof(last));
            for (int s = 0; s < t; ++s) {
                last[changeIndices[s]] = s;
            }
            int decrement = 0, marked = 0;
            for (int s = 0; s < t; ++s) {
                int i = changeIndices[s];
                if (last[i] == s) {
                    if (decrement < nums[i - 1]) {
                        return false;
                    }
                    decrement -= nums[i - 1];
                    ++marked;
                } else {
                    ++decrement;
                }
            }
            return marked == n;
        };

        int m = changeIndices.size();
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }
};