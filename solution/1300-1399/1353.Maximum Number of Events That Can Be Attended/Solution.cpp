class Solution {
public:
    int maxEvents(vector<vector<int>>& events) {
        unordered_map<int, vector<int>> d;
        int i = INT_MAX, j = 0;
        for (auto& v : events) {
            int s = v[0], e = v[1];
            d[s].push_back(e);
            i = min(i, s);
            j = max(j, e);
        }
        priority_queue<int, vector<int>, greater<int>> q;
        int ans = 0;
        for (int s = i; s <= j; ++s) {
            while (q.size() && q.top() < s) {
                q.pop();
            }
            for (int e : d[s]) {
                q.push(e);
            }
            if (q.size()) {
                ++ans;
                q.pop();
            }
        }
        return ans;
    }
};