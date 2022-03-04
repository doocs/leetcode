class Solution {
public:
    vector<int> p;

    int removeStones(vector<vector<int>>& stones) {
        int n = 10010;
        p.resize(n << 1);
        for (int i = 0; i < p.size(); ++i) p[i] = i;
        for (auto& stone : stones) p[find(stone[0])] = find(stone[1] + n);
        unordered_set<int> s;
        for (auto& stone : stones) s.insert(find(stone[0]));
        return stones.size() - s.size();
    }

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }
};