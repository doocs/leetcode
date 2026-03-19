class Solution {
public:
    int minOperations(string s, int k) {
        int n = s.size();

        set<int> ts[2];
        for (int i = 0; i <= n; i++) {
            ts[i % 2].insert(i);
        }

        int cnt0 = count(s.begin(), s.end(), '0');
        ts[cnt0 % 2].erase(cnt0);

        queue<int> q;
        q.push(cnt0);

        int ans = 0;

        while (!q.empty()) {
            for (int size = q.size(); size > 0; --size) {
                int cur = q.front();
                q.pop();
                if (cur == 0) {
                    return ans;
                }

                int l = cur + k - 2 * min(cur, k);
                int r = cur + k - 2 * max(k - n + cur, 0);

                auto& t = ts[l % 2];
                auto it = t.lower_bound(l);

                while (it != t.end() && *it <= r) {
                    q.push(*it);
                    it = t.erase(it);
                }
            }
            ans++;
        }

        return -1;
    }
};
