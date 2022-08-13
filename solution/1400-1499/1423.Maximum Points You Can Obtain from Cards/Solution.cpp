class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        vector<int> s(n + 1);
        for (int i = 0; i < n; ++i) s[i + 1] = s[i] + cardPoints[i];
        int mi = INT_MAX;
        for (int i = 0; i < n; ++i) {
            int j = i + (n - k) - 1;
            if (j < n) mi = min(mi, s[j + 1] - s[i]);
        }
        return s[n] - mi;
    }
};