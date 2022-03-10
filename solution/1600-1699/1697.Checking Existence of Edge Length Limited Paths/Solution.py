class Solution:
    def distanceLimitedPathsExist(
        self, n: int, edgeList: List[List[int]], queries: List[List[int]]
    ) -> List[bool]:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        edgeList.sort(key=lambda x: x[2])

        m = len(queries)
        indexes = list(range(m))
        indexes.sort(key=lambda i: queries[i][2])
        ans = [False] * m
        i = 0
        for j in indexes:
            pj, qj, limit = queries[j]
            while i < len(edgeList) and edgeList[i][2] < limit:
                u, v, _ = edgeList[i]
                p[find(u)] = find(v)
                i += 1
            ans[j] = find(pj) == find(qj)
        return ans
