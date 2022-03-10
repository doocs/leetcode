class Solution:
    def earliestAcq(self, logs: List[List[int]], n: int) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        p = list(range(n))
        logs.sort()
        for t, a, b in logs:
            if find(a) == find(b):
                continue
            p[find(a)] = find(b)
            n -= 1
            if n == 1:
                return t
        return -1
