class Solution {
public:
    int numberOfArrays(vector<int>& differences, int lower, int upper) {
        long long num = 0, mi = 0, mx = 0;
        for (int& d : differences) {
            num += d;
            mi = min(mi, num);
            mx = max(mx, num);
        }
        return max(0, (int)(upper - lower - (mx - mi) + 1));
    }
};