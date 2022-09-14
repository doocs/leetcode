using pii = pair<int, int>;

class Solution {
public:
    int findMaximizedCapital(int k, int w, vector<int>& profits, vector<int>& capital) {
        priority_queue<pii, vector<pii>, greater<pii>> q1;
        int n = profits.size();
        for (int i = 0; i < n; ++i) {
            q1.push({capital[i], profits[i]});
        }
        priority_queue<int> q2;
        while (k--) {
            while (!q1.empty() && q1.top().first <= w) {
                q2.push(q1.top().second);
                q1.pop();
            }
            if (q2.empty()) {
                break;
            }
            w += q2.top();
            q2.pop();
        }
        return w;
    }
};