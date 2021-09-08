class Solution {
public:
    int leastMinutes(int n) {
        int speed = 1, res = 1;
        while (speed < n)
        {
            speed <<= 1;
            ++res;
        }
        return res;
    }
};