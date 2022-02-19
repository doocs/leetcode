class Solution {
public:
    int leastMinutes(int n) {
        int ans = 1;
        for (int speed = 1; speed < n; speed <<= 1) ++ans;
        return ans;
    }
};