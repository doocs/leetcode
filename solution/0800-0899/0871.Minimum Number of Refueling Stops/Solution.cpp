class Solution {
public:
    int minRefuelStops(int target, int startFuel, vector<vector<int>>& stations) {
        priority_queue<int> q;
        stations.push_back({target, 0});
        int ans = 0, prev = 0;
        for (auto& s : stations) {
            int d = s[0] - prev;
            startFuel -= d;
            while (startFuel < 0 && !q.empty()) {
                startFuel += q.top();
                q.pop();
                ++ans;
            }
            if (startFuel < 0) return -1;
            q.push(s[1]);
            prev = s[0];
        }
        return ans;
    }
};