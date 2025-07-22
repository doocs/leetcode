class Solution {
public:
    int bitwiseComplement(int n) {
        if (n == 0) {
            return 1;
        }
        int ans = 0, i = 0;
        while (n != 0) {
            ans |= (n & 1 ^ 1) << (i++);
            n >>= 1;
        }
        return ans;
    }
};
