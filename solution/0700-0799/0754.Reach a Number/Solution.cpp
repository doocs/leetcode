class Solution {
public:
    int reachNumber(int target) {
        target = abs(target);
        int s = 0, k = 0;
        while (1) {
            if (s >= target && (s - target) % 2 == 0) return k;
            ++k;
            s += k;
        }
    }
};