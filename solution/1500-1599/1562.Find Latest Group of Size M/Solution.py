class Solution:
    def findLatestStep(self, arr: List[int], m: int) -> int:
        def find(x):
            if p[x] != x:
                p[x] = find(p[x])
            return p[x]

        def union(a, b):
            pa, pb = find(a), find(b)
            if pa == pb:
                return
            p[pa] = pb
            size[pb] += size[pa]

        n = len(arr)
        if m == n:
            return n
        vis = [False] * n
        p = list(range(n))
        size = [1] * n
        ans = -1
        for i, v in enumerate(arr):
            v -= 1
            if v and vis[v - 1]:
                if size[find(v - 1)] == m:
                    ans = i
                union(v, v - 1)
            if v < n - 1 and vis[v + 1]:
                if size[find(v + 1)] == m:
                    ans = i
                union(v, v + 1)
            vis[v] = True
        return ans
