class Solution:
    def removeOnes(self, grid: List[List[int]]) -> int:
        m, n = len(grid), len(grid[0])
        state = sum(1 << (i * n + j) for i in range(m) for j in range(n) if grid[i][j])
        q = deque([state])
        vis = {state}
        ans = 0
        while q:
            for _ in range(len(q)):
                state = q.popleft()
                if state == 0:
                    return ans
                for i in range(m):
                    for j in range(n):
                        if grid[i][j] == 0:
                            continue
                        nxt = state
                        for r in range(m):
                            nxt &= ~(1 << (r * n + j))
                        for c in range(n):
                            nxt &= ~(1 << (i * n + c))
                        if nxt not in vis:
                            vis.add(nxt)
                            q.append(nxt)
            ans += 1
        return -1
