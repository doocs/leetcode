class Solution:
    def numberOfPatterns(self, m: int, n: int) -> int:
        def dfs(i: int, cnt: int = 1) -> int:
            if cnt > n:
                return 0
            vis[i] = True
            ans = int(cnt >= m)
            for j in range(1, 10):
                x = cross[i][j]
                if not vis[j] and (x == 0 or vis[x]):
                    ans += dfs(j, cnt + 1)
            vis[i] = False
            return ans

        cross = [[0] * 10 for _ in range(10)]
        cross[1][3] = cross[3][1] = 2
        cross[1][7] = cross[7][1] = 4
        cross[1][9] = cross[9][1] = 5
        cross[2][8] = cross[8][2] = 5
        cross[3][7] = cross[7][3] = 5
        cross[3][9] = cross[9][3] = 6
        cross[4][6] = cross[6][4] = 5
        cross[7][9] = cross[9][7] = 8
        vis = [False] * 10
        return dfs(1) * 4 + dfs(2) * 4 + dfs(5)
