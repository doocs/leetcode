using pii = pair<int, int>;

class Solution {
public:
    int smallestChair(vector<vector<int>>& times, int targetFriend) {
        priority_queue<int, vector<int>, greater<int>> q;
        priority_queue<pii, vector<pii>, greater<pii>> busy;
        int n = times.size();
        for (int i = 0; i < n; ++i) {
            times[i].push_back(i);
            q.push(i);
        }
        sort(times.begin(), times.end());
        for (auto& t : times) {
            int a = t[0], b = t[1], i = t[2];
            while (!busy.empty() && busy.top().first <= a) {
                q.push(busy.top().second);
                busy.pop();
            }
            int c = q.top();
            q.pop();
            if (i == targetFriend) return c;
            busy.push({b, c});
        }
        return -1;
    }
};