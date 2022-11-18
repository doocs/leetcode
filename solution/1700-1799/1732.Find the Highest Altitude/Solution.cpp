class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int ans = 0, s = 0;
        for (int v : gain) s += v, ans = max(ans, s);
        return ans;
    }
};