class Solution {
public:
    vector<int> p;

    int removeStones(vector<vector<int>> &stones) {
        int n = 10010;
        p.resize(n << 1);
        for (int i = 0; i < p.size(); ++i)
            p[i] = i;
        for (auto e : stones)
        {
            p[find(e[0])] = find(e[1] + 10010);
        }
        unordered_set<int> s;
        for (auto e : stones)
        {
            s.insert(find(e[0]));
        }
        return stones.size() - s.size();
    }

    int find(int x) {
        if (p[x] != x)
            p[x] = find(p[x]);
        return p[x];
    }
};