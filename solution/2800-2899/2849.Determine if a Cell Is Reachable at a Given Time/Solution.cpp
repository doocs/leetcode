class Solution {
public:
    bool isReachableAtTime(int sx, int sy, int fx, int fy, int t) {
        if (sx == fx && sy == fy) {
            return t != 1;
        }
        int dx = abs(fx - sx), dy = abs(fy - sy);
        return max(dx, dy) <= t;
    }
};