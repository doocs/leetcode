class Solution {
public:
    int closetTarget(vector<string>& words, string target, int startIndex) {
        int n = words.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            auto w = words[i];
            if (w == target) {
                int t = abs(i - startIndex);
                ans = min(ans, min(t, n - t));
            }
        }
        return ans == n ? -1 : ans;
    }
};