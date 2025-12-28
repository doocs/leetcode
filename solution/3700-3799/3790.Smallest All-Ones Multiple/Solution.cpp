class Solution {
public:
    int minAllOneMultiple(int k) {
        if ((k & 1) == 0) {
            return -1;
        }

        int x = 1 % k;
        int ans = 1;

        for (int i = 0; i < k; ++i) {
            x = (x * 10 + 1) % k;
            ++ans;
            if (x == 0) {
                return ans;
            }
        }

        return -1;
    }
};
