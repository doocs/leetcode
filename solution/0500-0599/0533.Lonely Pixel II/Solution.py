class Solution:
    def findBlackPixel(self, picture: List[List[str]], target: int) -> int:
        m, n = len(picture), len(picture[0])
        rows = [0] * m
        cols = defaultdict(list)
        for i in range(m):
            for j in range(n):
                if picture[i][j] == 'B':
                    rows[i] += 1
                    cols[j].append(i)
        t = [[False] * m for _ in range(m)]
        for i in range(m):
            for k in range(i, m):
                if i == k:
                    t[i][k] = True
                else:
                    t[i][k] = all([picture[i][j] == picture[k][j] for j in range(n)])
                t[k][i] = t[i][k]
        res = 0
        for i in range(m):
            if rows[i] == target:
                for j in range(n):
                    if len(cols[j]) == target and all([t[i][k] for k in cols[j]]):
                        res += 1
        return res
