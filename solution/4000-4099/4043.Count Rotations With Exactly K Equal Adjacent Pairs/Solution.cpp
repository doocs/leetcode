class Solution {
public:
    int countRotations(string s, int k) {
        int n = s.size();
        int score = 0;

        for (int i = 0; i < n - 1; i++) {
            score += s[i] == s[i + 1];
        }

        int ans = score == k;

        for (int i = n; i < n * 2 - 1; i++) {
            score += s[i % n] == s[(i - 1) % n];
            score -= s[i % n] == s[(i + 1) % n];
            ans += score == k;
        }

        return ans;
    }
};