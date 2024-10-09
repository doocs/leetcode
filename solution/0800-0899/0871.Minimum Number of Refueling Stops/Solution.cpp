class Solution {
public:
    int minRefuelStops(int target, int startFuel, vector<vector<int>>& stations) {
        priority_queue<int> pq;
        stations.push_back({target, 0});
        int ans = 0, pre = 0;
        for (const auto& station : stations) {
            int pos = station[0], fuel = station[1];
            int dist = pos - pre;
            startFuel -= dist;
            while (startFuel < 0 && !pq.empty()) {
                startFuel += pq.top();
                pq.pop();
                ++ans;
            }
            if (startFuel < 0) {
                return -1;
            }
            pq.push(fuel);
            pre = pos;
        }
        return ans;
    }
};
