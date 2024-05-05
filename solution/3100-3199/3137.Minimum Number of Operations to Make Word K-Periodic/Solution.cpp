class Solution {
public:
    int minimumOperationsToMakeKPeriodic(string word, int k) {
        unordered_map<string, int> cnt;
        int n = word.size();
        int mx = 0;
        for (int i = 0; i < n; i += k) {
            mx = max(mx, ++cnt[word.substr(i, k)]);
        }
        return n / k - mx;
    }
};