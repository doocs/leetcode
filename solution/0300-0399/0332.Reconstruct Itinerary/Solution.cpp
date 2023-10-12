class Solution {
public:
    vector<string> findItinerary(vector<vector<string>>& tickets) {
        unordered_map<string, priority_queue<string, vector<string>, greater<string>>> g;
        vector<string> ret;

        // Initialize the graph
        for (const auto& t : tickets) {
            g[t[0]].push(t[1]);
        }

        findItineraryInner(g, ret, "JFK");

        ret = {ret.rbegin(), ret.rend()};

        return ret;
    }

    void findItineraryInner(unordered_map<string, priority_queue<string, vector<string>, greater<string>>>& g, vector<string>& ret, string cur) {
        if (g.count(cur) == 0) {
            // This is the end point
            ret.push_back(cur);
            return;
        } else {
            while (!g[cur].empty()) {
                auto front = g[cur].top();
                g[cur].pop();
                findItineraryInner(g, ret, front);
            }
            ret.push_back(cur);
        }
    }
};