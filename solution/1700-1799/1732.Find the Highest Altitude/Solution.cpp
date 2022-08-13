class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int res = 0, t = 0;
        for (int h : gain) {
            t += h;
            res = max(res, t);
        }
        return res;
    }
};