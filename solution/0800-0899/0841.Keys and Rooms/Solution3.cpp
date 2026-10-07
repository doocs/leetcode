class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        vector<char> vis(n);
        vector<int> stk = {0};
        while (!stk.empty()) {
            int i = stk.back();
            stk.pop_back();
            if (vis[i]) {
                continue;
            }
            vis[i] = 1;
            for (int j : rooms[i]) {
                stk.push_back(j);
            }
        }
        for (char v : vis) {
            if (!v) {
                return false;
            }
        }
        return true;
    }
};
