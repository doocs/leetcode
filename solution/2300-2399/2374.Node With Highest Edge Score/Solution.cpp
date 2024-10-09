class Solution {
public:
    int edgeScore(vector<int>& edges) {
        int n = edges.size();
        vector<long long> cnt(n);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int j = edges[i];
            cnt[j] += i;
            if (cnt[ans] < cnt[j] || (cnt[ans] == cnt[j] && j < ans)) {
                ans = j;
            }
        }
        return ans;
    }
};
