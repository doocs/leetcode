class Solution {
public:
    vector<int> kthSmallestPrimeFraction(vector<int>& arr, int k) {
        using pii = pair<int, int>;
        int n = arr.size();
        auto cmp = [&](const pii& a, const pii& b) {
            return arr[a.first] * arr[b.second] > arr[b.first] * arr[a.second];
        };
        priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
        for (int i = 1; i < n; ++i) {
            pq.push({0, i});
        }
        for (int i = 1; i < k; ++i) {
            pii f = pq.top();
            pq.pop();
            if (f.first + 1 < f.second) {
                pq.push({f.first + 1, f.second});
            }
        }
        return {arr[pq.top().first], arr[pq.top().second]};
    }
};
