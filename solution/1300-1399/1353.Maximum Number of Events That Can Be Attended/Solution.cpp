class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        unordered_map<int, vector<int>> g;
        int l = INT_MAX, r = 0;
        for (auto& event : events) {
            int s = event[0], e = event[1];
            g[s].push_back(e);
            l = min(l, s);
            r = max(r, e);
        }
        priority_queue<int, vector<int>, greater<int>> pq;
        int ans = 0;
        for (int s = l; s <= r; ++s) {
            while (!pq.empty() && pq.top() < s) {
                pq.pop();
            }
            for (int e : g[s]) {
                pq.push(e);
            }
            if (!pq.empty()) {
                pq.pop();
                ++ans;
            }
        }
        return ans;
    }
};
