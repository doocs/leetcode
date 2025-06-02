class Solution:
    def minMoves(self, classroom: List[str], energy: int) -> int:
        m, n = len(classroom), len(classroom[0])
        d = [[0] * n for _ in range(m)]
        x = y = cnt = 0
        for i, row in enumerate(classroom):
            for j, c in enumerate(row):
                if c == "S":
                    x, y = i, j
                elif c == "L":
                    d[i][j] = cnt
                    cnt += 1
        if cnt == 0:
            return 0
        vis = [
            [[[False] * (1 << cnt) for _ in range(energy + 1)] for _ in range(n)]
            for _ in range(m)
        ]
        q = [(x, y, energy, (1 << cnt) - 1)]
        vis[x][y][energy][(1 << cnt) - 1] = True
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        while q:
            t = q
            q = []
            for i, j, cur_energy, mask in t:
                if mask == 0:
                    return ans
                if cur_energy <= 0:
                    continue
                for k in range(4):
                    x, y = i + dirs[k], j + dirs[k + 1]
                    if 0 <= x < m and 0 <= y < n and classroom[x][y] != "X":
                        nxt_energy = (
                            energy if classroom[x][y] == "R" else cur_energy - 1
                        )
                        nxt_mask = mask
                        if classroom[x][y] == "L":
                            nxt_mask &= ~(1 << d[x][y])
                        if not vis[x][y][nxt_energy][nxt_mask]:
                            vis[x][y][nxt_energy][nxt_mask] = True
                            q.append((x, y, nxt_energy, nxt_mask))
            ans += 1
        return -1
