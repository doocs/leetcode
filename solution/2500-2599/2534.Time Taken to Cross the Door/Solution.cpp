class Solution {
public:
    vector<int> timeTaken(vector<int>& arrival, vector<int>& state) {
        int n = arrival.size();
        queue<int> q[2];
        int t = 0, i = 0, st = 1;
        vector<int> ans(n);

        while (i < n || !q[0].empty() || !q[1].empty()) {
            while (i < n && arrival[i] <= t) {
                q[state[i]].push(i++);
            }

            if (!q[0].empty() && !q[1].empty()) {
                ans[q[st].front()] = t;
                q[st].pop();
            } else if (!q[0].empty() || !q[1].empty()) {
                st = q[0].empty() ? 1 : 0;
                ans[q[st].front()] = t;
                q[st].pop();
            } else {
                st = 1;
            }

            ++t;
        }

        return ans;
    }
};
