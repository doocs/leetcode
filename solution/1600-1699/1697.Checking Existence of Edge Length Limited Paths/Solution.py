class Solution:
    def distanceLimitedPathsExist(self, n: int, edgeList: List[List[int]], queries: List[List[int]]) -> List[bool]:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        edgeList.sort(key=lambda x: x[2])
        m = len(queries)
        indexes = list(range(m))
        indexes.sort(key=lambda x: queries[x][2])
        ans = [False] * m
        i = 0
        for j in indexes:
            pj, qj, limit = queries[j][0], queries[j][1], queries[j][2]
            while i < len(edgeList) and edgeList[i][2] < limit:
                u, v = edgeList[i][0], edgeList[i][1]
                p[find(u)] = find(v)
                i += 1
            ans[j] = find(pj) == find(qj)
        return ans
