class Solution:
    def minCostToSupplyWater(
        self, n: int, wells: List[int], pipes: List[List[int]]
    ) -> int:
        for i, w in enumerate(wells):
            pipes.append([0, i + 1, w])
        pipes.sort(key=lambda x: x[2])

        p = list(range(n + 1))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        res = 0
        for u, v, w in pipes:
            if find(u) == find(v):
                continue
            p[find(u)] = find(v)
            res += w
            n -= 1
            if n == 0:
                break
        return res
