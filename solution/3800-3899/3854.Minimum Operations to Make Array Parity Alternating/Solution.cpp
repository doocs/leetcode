class Solution {
public:
    vector<int> makeParityAlternating(vector<int>& nums) {
        if (nums.size() == 1) {
            return {0, 0};
        }

        auto [mnIt, mxIt] = minmax_element(nums.begin(), nums.end());
        int mn = *mnIt;
        int mx = *mxIt;

        auto f = [&](int k) {
            int cnt = 0;
            int a = INT_MAX;
            int b = INT_MIN;

            for (int i = 0; i < nums.size(); i++) {
                int x = nums[i];
                if (((x - i) & 1) != k) {
                    cnt++;
                    if (x == mn) {
                        ++x;
                    } else if (x == mx) {
                        --x;
                    }
                }
                a = min(a, x);
                b = max(b, x);
            }
            return vector<int>{cnt, max(1, b - a)};
        };

        vector<int> r0 = f(0);
        vector<int> r1 = f(1);

        return r0 < r1 ? r0 : r1;
    }
};
