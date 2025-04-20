class Solution {
public:
    long long calculateScore(vector<string>& instructions, vector<int>& values) {
        int n = values.size();
        vector<bool> vis(n, false);
        long long ans = 0;
        int i = 0;

        while (i >= 0 && i < n && !vis[i]) {
            vis[i] = true;
            if (instructions[i][0] == 'a') {
                ans += values[i];
                i += 1;
            } else {
                i += values[i];
            }
        }

        return ans;
    }
};
