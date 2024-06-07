class Solution {
public:
    int minMutation(string startGene, string endGene, vector<string>& bank) {
        queue<pair<string, int>> q{{{startGene, 0}}};
        unordered_set<string> vis = {startGene};
        while (!q.empty()) {
            auto [gene, depth] = q.front();
            q.pop();
            if (gene == endGene) {
                return depth;
            }
            for (const string& next : bank) {
                int c = 2;
                for (int k = 0; k < 8 && c; ++k) {
                    c -= gene[k] != next[k];
                }
                if (c && !vis.contains(next)) {
                    vis.insert(next);
                    q.push({next, depth + 1});
                }
            }
        }
        return -1;
    }
};