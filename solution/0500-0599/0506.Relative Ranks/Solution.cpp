class Solution {
public:
    vector<string> findRelativeRanks(vector<int>& score) {
        int n = score.size();
        vector<pair<int, int>> idx;
        for (int i = 0; i < n; ++i)
            idx.push_back(make_pair(score[i], i));
        sort(idx.begin(), idx.end(),
            [&](const pair<int, int>& x, const pair<int, int>& y) { return x.first > y.first; });
        vector<string> ans(n);
        vector<string> top3 = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        for (int i = 0; i < n; ++i)
            ans[idx[i].second] = i < 3 ? top3[i] : to_string(i + 1);
        return ans;
    }
};
