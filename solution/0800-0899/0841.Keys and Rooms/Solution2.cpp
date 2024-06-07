class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        int n = rooms.size();
        vector<bool> vis(n);
        queue<int> q{{0}};
        int cnt = 0;
        while (q.size()) {
            int i = q.front();
            q.pop();
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            ++cnt;
            for (int j : rooms[i]) {
                q.push(j);
            }
        }
        return cnt == n;
    }
};
