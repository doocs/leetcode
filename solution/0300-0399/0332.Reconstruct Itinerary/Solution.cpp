class Solution {
public:
    std::vector<std::string> findItinerary(std::vector<std::vector<std::string>> &tickets) {
        std::unordered_map<std::string, std::priority_queue<std::string, std::vector<std::string>, std::greater<std::string>>> g;
        std::vector<std::string> ret;

        // Initialize the graph
        for (const auto &t : tickets) {
            g[t[0]].push(t[1]);
        }

        findItineraryInner(g, ret, "JFK");

        ret = {ret.rbegin(), ret.rend()};

        return ret;
    }

    void findItineraryInner(std::unordered_map<std::string, std::priority_queue<std::string, std::vector<std::string>, std::greater<std::string>>> &g, std::vector<std::string> &ret, std::string cur) {
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