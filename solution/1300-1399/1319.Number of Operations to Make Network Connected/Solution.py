class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        cnt = 0
        p = list(range(n))
        for a, b in connections:
            if find(a) == find(b):
                cnt += 1
            else:
                p[find(a)] = find(b)
                n -= 1
        return -1 if n - 1 > cnt else n - 1
