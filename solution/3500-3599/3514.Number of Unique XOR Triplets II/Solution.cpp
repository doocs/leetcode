class Solution {
public:
    int uniqueXorTriplets(vector<int>& nums) {
        int mx = ranges::max(nums) << 1;

        vector<bool> st(mx, false);
        for (int a : nums) {
            for (int b : nums) {
                st[a ^ b] = true;
            }
        }

        vector<int> s(mx, 0);
        for (int ab = 0; ab < mx; ab++) {
            if (st[ab]) {
                for (int c : nums) {
                    s[ab ^ c] = 1;
                }
            }
        }

        return accumulate(s.begin(), s.end(), 0);
    }
};