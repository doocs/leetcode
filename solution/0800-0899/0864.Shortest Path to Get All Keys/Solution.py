class Solution:
    def shortestPathAllKeys(self, grid: List[str]) -> int:
        m, n = len(grid), len(grid[0])
        # 找起点 (si, sj)
        si, sj = next((i, j) for i in range(m) for j in range(n) if grid[i][j] == '@')
        # 统计钥匙数量
        k = sum(v.islower() for row in grid for v in row)
        dirs = (-1, 0, 1, 0, -1)
        q = deque([(si, sj, 0)])
        vis = {(si, sj, 0)}
        ans = 0
        while q:
            for _ in range(len(q)):
                i, j, state = q.popleft()
                # 找到所有钥匙，返回当前步数
                if state == (1 << k) - 1:
                    return ans

                # 往四个方向搜索
                for a, b in pairwise(dirs):
                    x, y = i + a, j + b
                    nxt = state
                    # 在边界范围内
                    if 0 <= x < m and 0 <= y < n:
                        c = grid[x][y]
                        # 是墙，或者是锁，但此时没有对应的钥匙，无法通过
                        if (
                            c == '#'
                            or c.isupper()
                            and (state & (1 << (ord(c) - ord('A')))) == 0
                        ):
                            continue
                        # 是钥匙
                        if c.islower():
                            # 更新状态
                            nxt |= 1 << (ord(c) - ord('a'))
                        # 此状态未访问过，入队
                        if (x, y, nxt) not in vis:
                            vis.add((x, y, nxt))
                            q.append((x, y, nxt))
            # 步数加一
            ans += 1
        return -1
