class Solution {
public:
    vector<vector<int>> rooms;
    unordered_set<int> vis;

    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        vis.clear();
        this->rooms = rooms;
        dfs(0);
        return vis.size() == rooms.size();
    }

    void dfs(int u) {
        if (vis.count(u)) return;
        vis.insert(u);
        for (int v : rooms[u]) dfs(v);
    }
};