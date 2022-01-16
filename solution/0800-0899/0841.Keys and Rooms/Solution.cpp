class Solution {
public:
    vector<vector<int>> rooms;
    unordered_set<int> vis;
    int n;

    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        vis.clear();
        this->rooms = rooms;
        n = rooms.size();
        dfs(0);
        return vis.size() == n;
    }

    void dfs(int u) {
        if (u == n || vis.count(u)) return;
        vis.insert(u);
        for (int v : rooms[u]) dfs(v);
    }
};