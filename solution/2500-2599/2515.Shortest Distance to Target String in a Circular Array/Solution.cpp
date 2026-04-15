class Solution {
public:
    int closestTarget(vector<string>& words, string target, int startIndex) {
        int n = words.size();
        int ans = n;
        for (int i = 0; i < n; i++) {
            if (words[i] == target) {
                int t = abs(i - startIndex);
                ans = min({ans, t, n - t});
            }
        }
        return ans == n ? -1 : ans;
    }
};
