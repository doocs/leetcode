class Solution:
    def minCostToSupplyWater(
        self, n: int, wells: List[int], pipes: List[List[int]]
    ) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        for i, w in enumerate(wells, 1):
            pipes.append([0, i, w])
        pipes.sort(key=lambda x: x[2])
        p = list(range(n + 1))
        ans = 0
        for i, j, c in pipes:
            pa, pb = find(i), find(j)
            if pa == pb:
                continue
            p[pa] = pb
            ans += c
            n -= 1
            if n == 0:
                break
        return ans
