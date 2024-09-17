class Solution {
public:
    int numBusesToDestination(vector<vector<int>>& routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        unordered_map<int, vector<int>> g;
        for (int i = 0; i < routes.size(); i++) {
            for (int stop : routes[i]) {
                g[stop].push_back(i);
            }
        }

        if (!g.contains(source) || !g.contains(target)) {
            return -1;
        }

        queue<pair<int, int>> q;
        unordered_set<int> visBus;
        unordered_set<int> visStop;
        q.push({source, 0});
        visStop.insert(source);

        while (!q.empty()) {
            auto [stop, busCount] = q.front();
            q.pop();

            if (stop == target) {
                return busCount;
            }

            for (int bus : g[stop]) {
                if (!visBus.contains(bus)) {
                    for (int nextStop : routes[bus]) {
                        if (!visStop.contains(nextStop)) {
                            visBus.insert(bus);
                            visStop.insert(nextStop);
                            q.push({nextStop, busCount + 1});
                        }
                    }
                }
            }
        }

        return -1;
    }
};
