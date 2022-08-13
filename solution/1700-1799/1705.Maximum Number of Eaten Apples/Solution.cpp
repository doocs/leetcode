typedef pair<int, int> PII;

class Solution {
public:
    int eatenApples(vector<int>& apples, vector<int>& days) {
        priority_queue<PII, vector<PII>, greater<PII>> q;
        int i = 0, n = apples.size(), ans = 0;
        while (i < n || !q.empty()) {
            if (i < n && apples[i] > 0) q.emplace(i + days[i] - 1, apples[i]);
            while (!q.empty() && q.top().first < i) q.pop();
            if (!q.empty()) {
                PII t = q.top();
                q.pop();
                --t.second;
                ++ans;
                if (t.second > 0 && t.first > i) q.emplace(t);
            }
            ++i;
        }
        return ans;
    }
};