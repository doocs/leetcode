class Solution {
    int cnt;
    int dis[10][10];
    int dir_x[4]={0, 1, 0, -1};
    int dir_y[4]={1, 0, -1, 0};
public:
    int orangesRotting(vector < vector < int >> & grid) {
        queue < pair < int, int > > Q;
        memset(dis, -1, sizeof(dis));
        cnt = 0;
        int n = (int) grid.size(), m = (int) grid[0].size(), ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (grid[i][j] == 2) {
                    Q.push(make_pair(i, j));
                    dis[i][j] = 0;
                } 
		else if (grid[i][j] == 1) cnt += 1;
            }
        }
        while (!Q.empty()) {
            pair < int, int > x = Q.front();
            Q.pop();
            for (int i = 0; i < 4; ++i) {
                int tx = x.first + dir_x[i];
                int ty = x.second + dir_y[i];
                if (tx < 0 || tx >= n || ty < 0 || ty >= m || ~dis[tx][ty] || !grid[tx][ty]) continue;
                dis[tx][ty] = dis[x.first][x.second] + 1;
                Q.push(make_pair(tx, ty));
                if (grid[tx][ty] == 1) {
                    cnt -= 1;
                    ans = dis[tx][ty];
                    if (!cnt) break;
                }
            }
        }
        return cnt ? -1 : ans;
    }
};