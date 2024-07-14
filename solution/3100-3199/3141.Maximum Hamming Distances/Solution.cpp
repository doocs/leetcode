class Solution {
public:
    vector<int> maxHammingDistances(vector<int>& nums, int m) {
        int dist[1 << m];
        memset(dist, -1, sizeof(dist));
        queue<int> q;
        for (int x : nums) {
            dist[x] = 0;
            q.push(x);
        }
        for (int k = 1; q.size(); ++k) {
            for (int t = q.size(); t; --t) {
                int x = q.front();
                q.pop();
                for (int i = 0; i < m; ++i) {
                    int y = x ^ (1 << i);
                    if (dist[y] == -1) {
                        dist[y] = k;
                        q.push(y);
                    }
                }
            }
        }
        for (int& x : nums) {
            x = m - dist[x ^ ((1 << m) - 1)];
        }
        return nums;
    }
};