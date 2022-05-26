class Solution {
public:
    int getLastMoment(int n, vector<int>& left, vector<int>& right) {
        int ans = 0;
        for (int t : left) ans = max(ans, t);
        for (int t : right) ans = max(ans, n - t);
        return ans;
    }
};