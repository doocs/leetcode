class Solution {
public:
    int getLastMoment(int n, vector<int>& left, vector<int>& right) {
        int ans = 0;
        for (int& x : left) {
            ans = max(ans, x);
        }
        for (int& x : right) {
            ans = max(ans, n - x);
        }
        return ans;
    }
};