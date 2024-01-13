class Solution:
    def extractMantra(self, matrix: List[str], mantra: str) -> int:
        m, n = len(matrix), len(matrix[0])
        q = deque([(0, 0, 0)])
        vis = {q[0]}
        dirs = (-1, 0, 1, 0, -1)
        ans = 0
        while q:
            for _ in range(len(q)):
                i, j, k = q.popleft()
                if k == len(mantra):
                    return ans
                if matrix[i][j] == mantra[k]:
                    t = (i, j, k + 1)
                    if t not in vis:
                        vis.add(t)
                        q.append(t)
                else:
                    for a, b in pairwise(dirs):
                        x, y = i + a, j + b
                        if 0 <= x < m and 0 <= y < n:
                            t = (x, y, k)
                            if t not in vis:
                                vis.add(t)
                                q.append(t)
            ans += 1
        return -1
