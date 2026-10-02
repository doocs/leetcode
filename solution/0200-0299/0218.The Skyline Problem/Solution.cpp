class Solution {
public:
    vector<vector<int>> getSkyline(vector<vector<int>>& buildings) {
        vector<int> lines;
        for (auto& v : buildings) {
            lines.push_back(v[0]);
            lines.push_back(v[1]);
        }
        sort(lines.begin(), lines.end());
        priority_queue<pair<int, int>> pq;
        vector<vector<int>> skys;
        int city = 0, n = buildings.size();
        for (int line : lines) {
            while (city < n && buildings[city][0] <= line) {
                pq.emplace(buildings[city][2], buildings[city][1]);
                ++city;
            }
            while (!pq.empty() && pq.top().second <= line) {
                pq.pop();
            }
            int high = pq.empty() ? 0 : pq.top().first;
            if (!skys.empty() && skys.back()[1] == high) {
                continue;
            }
            skys.push_back({line, high});
        }
        return skys;
    }
};
