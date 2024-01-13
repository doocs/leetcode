class Solution {
public:
    int nthUglyNumber(int n) {
        priority_queue<long, vector<long>, greater<long>> q;
        q.push(1l);
        unordered_set<long> vis{{1l}};
        long ans = 1;
        vector<int> f = {2, 3, 5};
        while (n--) {
            ans = q.top();
            q.pop();
            for (int& v : f) {
                long nxt = ans * v;
                if (!vis.count(nxt)) {
                    vis.insert(nxt);
                    q.push(nxt);
                }
            }
        }
        return (int) ans;
    }
};