class Solution:
    def minimumMoves(self, grid: List[List[int]]) -> int:
        q = deque([tuple(tuple(row) for row in grid)])
        vis = set(q)
        ans = 0
        dirs = (-1, 0, 1, 0, -1)
        while 1:
            for _ in range(len(q)):
                cur = q.popleft()
                if all(x for row in cur for x in row):
                    return ans
                for i in range(3):
                    for j in range(3):
                        if cur[i][j] > 1:
                            for a, b in pairwise(dirs):
                                x, y = i + a, j + b
                                if 0 <= x < 3 and 0 <= y < 3 and cur[x][y] < 2:
                                    nxt = [list(row) for row in cur]
                                    nxt[i][j] -= 1
                                    nxt[x][y] += 1
                                    nxt = tuple(tuple(row) for row in nxt)
                                    if nxt not in vis:
                                        vis.add(nxt)
                                        q.append(nxt)
            ans += 1
