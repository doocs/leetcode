class Solution {
public:
    int maxScore(vector<int>& cardPoints, int k) {
        int n = cardPoints.size();
        int s = accumulate(cardPoints.end() - k, cardPoints.end(), 0);
        int ans = s;
        for (int i = 0; i < k; ++i) {
            s += cardPoints[i] - cardPoints[n - k + i];
            ans = max(ans, s);
        }
        return ans;
    }
};