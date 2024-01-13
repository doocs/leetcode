class Solution {
public:
    long long minimumHealth(vector<int>& damage, int armor) {
        long long s = 0;
        int mx = damage[0];
        for (int& v : damage) {
            s += v;
            mx = max(mx, v);
        }
        return s - min(mx, armor) + 1;
    }
};