class Solution:
    def minCost(self, n: int, edges: List[List[int]], k: int) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        if k == n:
            return 0
        edges.sort(key=lambda x: x[2])
        cnt = n
        p = list(range(n))
        for u, v, w in edges:
            pu, pv = find(u), find(v)
            if pu != pv:
                p[pu] = pv
                cnt -= 1
                if cnt <= k:
                    return w
        return 0
