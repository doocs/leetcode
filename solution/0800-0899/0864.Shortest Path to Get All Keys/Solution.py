class Solution:
    def shortestPathAllKeys(self, grid: List[str]) -> int:
        m, n = len(grid), len(grid[0])
        si, sj = next((i, j) for i in range(m) for j in range(n) if grid[i][j] == '@')
        k = sum(v.islower() for row in grid for v in row)
        dirs = (-1, 0, 1, 0, -1)
        q = deque([(si, sj, 0)])
        vis = {(si, sj, 0)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, j, state = q.popleft()
                if state == (1 << k) - 1:
                    return ans
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    nxt = state
                    if 0 <= x < m and 0 <= y < n:
                        c = grid[x][y]
                        if (
                            c == '#'
                            or c.isupper()
                            and (state & (1 << (ord(c) - ord('A')))) == 0
                        ):
                            continue
                        if c.islower():
                            nxt |= 1 << (ord(c) - ord('a'))
                        if (x, y, nxt) not in vis:
                            vis.add((x, y, nxt))
                            q.append((x, y, nxt))
            ans += 1
        return -1
