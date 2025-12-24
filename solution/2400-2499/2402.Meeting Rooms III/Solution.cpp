class Solution {
public:
    int mostBooked(int n, vector<vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end());
        using pli = pair<long long, int>;
        priority_queue<pli, vector<pli>, greater<pli>> busy;
        priority_queue<int, vector<int>, greater<int>> idle;
        for (int i = 0; i < n; ++i) {
            idle.push(i);
        }
        vector<int> cnt(n);
        for (auto& v : meetings) {
            int s = v[0], e = v[1];
            while (!busy.empty() && busy.top().first <= s) {
                idle.push(busy.top().second);
                busy.pop();
            }
            int i = 0;
            if (!idle.empty()) {
                i = idle.top();
                idle.pop();
                busy.push({e, i});
            } else {
                auto x = busy.top();
                busy.pop();
                i = x.second;
                busy.push({x.first + e - s, i});
            }
            ++cnt[i];
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (cnt[ans] < cnt[i]) {
                ans = i;
            }
        }
        return ans;
    }
};
