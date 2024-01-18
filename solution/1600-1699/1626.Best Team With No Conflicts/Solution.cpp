class Solution {
public:
    int bestTeamScore(vector<int>& scores, vector<int>& ages) {
        int n = ages.size();
        vector<pair<int, int>> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = {scores[i], ages[i]};
        }
        sort(arr.begin(), arr.end());
        vector<int> f(n);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (arr[i].second >= arr[j].second) {
                    f[i] = max(f[i], f[j]);
                }
            }
            f[i] += arr[i].first;
        }
        return *max_element(f.begin(), f.end());
    }
};