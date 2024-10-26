class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        def find(x: int) -> int:
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        cnt = 0
        p = list(range(n))
        for a, b in connections:
            pa, pb = find(a), find(b)
            if pa == pb:
                cnt += 1
            else:
                p[pa] = pb
                n -= 1
        return -1 if n - 1 > cnt else n - 1
