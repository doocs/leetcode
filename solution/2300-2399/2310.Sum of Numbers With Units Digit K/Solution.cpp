class Solution {
public:
    int minimumNumbers(int num, int k) {
        if (num == 0) return 0;
        for (int i = 1; i <= num; ++i) {
            int t = num - k * i;
            if (t >= 0 && t % 10 == 0) return i;
        }
        return -1;
    }
};