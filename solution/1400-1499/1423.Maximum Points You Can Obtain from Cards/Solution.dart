class Solution {
  int maxScore(List<int> cardPoints, int k) {
    int n = cardPoints.length;
    int s = cardPoints.sublist(n - k).reduce((a, b) => a + b);
    int ans = s;
    for (int i = 0; i < k; ++i) {
      s += cardPoints[i] - cardPoints[n - k + i];
      ans = s > ans ? s : ans;
    }
    return ans;
  }
}