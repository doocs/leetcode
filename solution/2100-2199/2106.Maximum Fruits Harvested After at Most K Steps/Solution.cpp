class Solution {
public:
    int maxTotalFruits(vector<vector<int>>& fruits, int startPos, int k) {
        queue<vector<int>> q;
        int i = 0, n = fruits.size();
        int ans = 0;
        while (i < n && fruits[i][0] <= startPos) {
            if (startPos - fruits[i][0] <= k) {
                ans += fruits[i][1];
                q.push(fruits[i]);
            }
            ++i;
        }
        int t = ans;
        while (i < n && fruits[i][0] - startPos <= k) {
            while (!q.empty() && q.front()[0] < startPos && fruits[i][0] - q.front()[0] + min(startPos - q.front()[0], fruits[i][0] - startPos) > k) {
                t -= q.front()[1];
                q.pop();
            }
            t += fruits[i][1];
            ans = max(ans, t);
            ++i;
        }
        return ans;
    }
};