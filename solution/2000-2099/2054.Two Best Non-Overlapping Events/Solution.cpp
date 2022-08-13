class Solution {
public:
    int maxTwoEvents(vector<vector<int>>& events) {
        sort(events.begin(), events.end());
        int n = events.size();
        vector<int> f(n + 1);
        for (int i = n - 1; ~i; --i) f[i] = max(f[i + 1], events[i][2]);
        int ans = 0;
        for (auto& e : events) {
            int v = e[2];
            int left = 0, right = n;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (events[mid][0] > e[1])
                    right = mid;
                else
                    left = mid + 1;
            }
            if (left < n) v += f[left];
            ans = max(ans, v);
        }
        return ans;
    }
};