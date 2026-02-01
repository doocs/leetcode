class Solution {
public:
    int countMonobit(int n) {
        int ans = 1;
        for (int i = 1, x = 1; x <= n; ++i) {
            ++ans;
            x += (1 << i);
        }
        return ans;
    }
};
