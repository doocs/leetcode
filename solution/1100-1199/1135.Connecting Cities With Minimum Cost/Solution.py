class Solution:
    def minimumCost(self, n: int, connections: List[List[int]]) -> int:
        p = list(range(n))
        connections.sort(key=lambda x: x[2])
        res = 0

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a - 1), find(b - 1)
            if pa == pb:
                return False
            p[pa] = pb
            return True

        for c1, c2, cost in connections:
            if union(c1, c2):
                n -= 1
                res += cost
                if n == 1:
                    return res
        return -1
