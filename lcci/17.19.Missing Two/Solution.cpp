class Solution {
public:
    vector<int> missingTwo(vector<int>& nums) {
        int n = nums.size() + 2;
        int eor = 0;
        for (int v : nums) eor ^= v;
        for (int i = 1; i <= n; ++i) eor ^= i;

        int diff = eor & -eor;
        int a = 0;
        for (int v : nums)
            if (v & diff) a ^= v;
        for (int i = 1; i <= n; ++i)
            if (i & diff) a ^= i;
        int b = eor ^ a;
        return {a, b};
    }
};