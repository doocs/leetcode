class Solution {
public:
    const vector<int> factors = {3, 5, 7};

    int getKthMagicNumber(int k) {
        priority_queue<long, vector<long>, greater<long>> q;
        unordered_set<long> vis;
        q.push(1l);
        vis.insert(1l);
        for (int i = 0; i < k - 1; ++i) {
            long cur = q.top();
            q.pop();
            for (int f : factors) {
                long nxt = cur * f;
                if (!vis.count(nxt)) {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return (int) q.top();
    }
};