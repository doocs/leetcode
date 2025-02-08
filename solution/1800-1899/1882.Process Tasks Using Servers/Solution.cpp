class Solution {
public:
    vector<int> assignTasks(vector<int>& servers, vector<int>& tasks) {
        using pii = pair<int, int>;
        using arr3 = array<int, 3>;
        priority_queue<pii, vector<pii>, greater<pii>> idle;
        priority_queue<arr3, vector<arr3>, greater<arr3>> busy;
        for (int i = 0; i < servers.size(); ++i) {
            idle.push({servers[i], i});
        }
        int m = tasks.size();
        vector<int> ans(m);
        for (int j = 0; j < m; ++j) {
            int t = tasks[j];
            while (!busy.empty() && busy.top()[0] <= j) {
                auto [_, s, i] = busy.top();
                busy.pop();
                idle.push({s, i});
            }

            if (!idle.empty()) {
                auto [s, i] = idle.top();
                idle.pop();
                ans[j] = i;
                busy.push({j + t, s, i});
            } else {
                auto [w, s, i] = busy.top();
                busy.pop();
                ans[j] = i;
                busy.push({w + t, s, i});
            }
        }
        return ans;
    }
};
