#include <array>
#include <climits>
#include <queue>
#include <vector>
using namespace std;

class Solution {
public:
    int secondMinimum(int n, vector<vector<int>>& edges, int time, int change) {
        vector<vector<int>> adj(n + 1);
        for (auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }
        vector<array<int, 2>> dist(n + 1, {INT_MAX, INT_MAX});
        queue<pair<int, int>> q;
        dist[1][0] = 0;
        q.emplace(1, 0);

        while (!q.empty()) {
            auto [u, t] = q.front();
            q.pop();
            for (int v : adj[u]) {
                int cycles = t / change;
                int wait = (cycles % 2 == 1 ? change - (t % change) : 0);
                int t2 = t + wait + time;
                if (t2 < dist[v][0]) {
                    dist[v][1] = dist[v][0];
                    dist[v][0] = t2;
                    q.emplace(v, t2);
                } else if (t2 > dist[v][0] && t2 < dist[v][1]) {
                    dist[v][1] = t2;
                    q.emplace(v, t2);
                }
            }
        }

        return dist[n][1];
    }
};