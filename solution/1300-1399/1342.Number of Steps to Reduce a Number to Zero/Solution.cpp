class Solution {
public:
    int numberOfSteps(int num) {
        int ans = 0;
        while (num) {
            num = num & 1 ? num - 1 : num >> 1;
            ++ans;
        }
        return ans;
    }
};