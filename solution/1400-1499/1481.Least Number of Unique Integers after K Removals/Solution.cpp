class Solution {
public:
    int findLeastNumOfUniqueInts(vector<int>& arr, int k) {
        unordered_map<int, int> counter;
        for (int v : arr) ++counter[v];
        vector<pair<int, int>> t(counter.begin(), counter.end());
        sort(t.begin(), t.end(), [](const auto& a, const auto& b) { return a.second < b.second; });
        for (auto [v, cnt] : t) {
            if (k >= cnt) {
                k -= cnt;
                counter.erase(v);
            } else
                break;
        }
        return counter.size();
    }
};