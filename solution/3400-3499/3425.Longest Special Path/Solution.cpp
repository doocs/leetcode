#include <bits/stdc++.h>
using namespace std;

class Solution {
    struct Frame {
        int node, parent, left, depth, weight, old;
        bool exit;
    };

public:
    vector<int> longestSpecialPath(vector<vector<int>>& edges, vector<int>& nums) {
        int n = nums.size();

        vector<vector<pair<int, int>>> graph(n);

        for (auto& e : edges) {
            graph[e[0]].push_back({e[1], e[2]});
            graph[e[1]].push_back({e[0], e[2]});
        }

        unordered_map<int, int> last;
        vector<long long> prefix(n + 1, 0);

        vector<Frame> st;
        st.push_back({0, -1, 0, 0, 0, 0, false});

        long long bestLength = 0;
        int bestNodes = 1;

        while (!st.empty()) {
            Frame f = st.back();
            st.pop_back();

            int u = f.node;

            if (f.exit) {
                if (f.old == 0) {
                    last.erase(nums[u]);
                } else {
                    last[nums[u]] = f.old;
                }
                continue;
            }

            prefix[f.depth + 1] = prefix[f.depth] + f.weight;

            int old = last.count(nums[u]) ? last[nums[u]] : 0;
            int left = max(f.left, old);

            last[nums[u]] = f.depth + 1;

            long long length = prefix[f.depth + 1] - prefix[left + 1];
            int nodes = f.depth + 1 - left;

            if (length > bestLength ||
                (length == bestLength && nodes < bestNodes)) {
                bestLength = length;
                bestNodes = nodes;
            }

            st.push_back({
                u, f.parent, left, f.depth, 0, old, true
            });

            for (auto it = graph[u].rbegin(); it != graph[u].rend(); ++it) {
                int v = it->first;

                if (v != f.parent) {
                    st.push_back({
                        v, u, left, f.depth + 1,
                        it->second, 0, false
                    });
                }
            }
        }

        return {(int)bestLength, bestNodes};
    }
};
