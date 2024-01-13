class Solution {
public:
    int addRungs(vector<int>& rungs, int dist) {
        int ans = 0, prev = 0;
        for (int& x : rungs) {
            ans += (x - prev - 1) / dist;
            prev = x;
        }
        return ans;
    }
};