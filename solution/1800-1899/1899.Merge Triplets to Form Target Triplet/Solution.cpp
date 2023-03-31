class Solution {
public:
    bool mergeTriplets(vector<vector<int>>& triplets, vector<int>& target) {
        int x = target[0], y = target[1], z = target[2];
        int d = 0, e = 0, f = 0;
        for (auto& t : triplets) {
            int a = t[0], b = t[1], c = t[2];
            if (a <= x && b <= y && c <= z) {
                d = max(d, a);
                e = max(e, b);
                f = max(f, c);
            }
        }
        return d == x && e == y && f == z;
    }
};