class Solution:
    def makeConnected(self, n: int, connections: List[List[int]]) -> int:
        p = list(range(n))

        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        cnt = 0
        for a, b in connections:
            if find(a) == find(b):
                cnt += 1
            else:
                p[find(a)] = find(b)
        total = sum(i == find(i) for i in range(n))
        return -1 if total - 1 > cnt else total - 1
