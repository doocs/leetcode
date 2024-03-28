/** 
 * Definition of commonBits API.
 * int commonBits(int num);
 */

class Solution {
public:
    int findNumber() {
        int n = 0;
        for (int i = 0; i < 32; ++i) {
            int count1 = commonBits(1 << i);
            int count2 = commonBits(1 << i);
            if (count1 > count2) {
                n |= 1 << i;
            }
        }
        return n;
    }
};