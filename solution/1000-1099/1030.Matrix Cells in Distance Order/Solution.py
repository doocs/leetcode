class Solution:
    def allCellsDistOrder(
        self, rows: int, cols: int, rCenter: int, cCenter: int
    ) -> List[List[int]]:
        q = deque([(rCenter, cCenter)])
        vis = [[False] * cols for _ in range(rows)]
        vis[rCenter][cCenter] = True
        ans = []
        while q:
            for _ in range(len(q)):
                i, j = q.popleft()
                ans.append([i, j])
                for a, b in [[1, 0], [-1, 0], [0, 1], [0, -1]]:
                    x, y = i + a, j + b
                    if 0 <= x < rows and 0 <= y < cols and not vis[x][y]:
                        q.append((x, y))
                        vis[x][y] = True
        return ans
