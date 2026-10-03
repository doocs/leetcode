class Solution:
    def getCoprimes(self, nums: List[int], edges: List[List[int]]) -> List[int]:
        n = len(nums)
        g = [[] for _ in range(n)]
        for u, v in edges:
            g[u].append(v)
            g[v].append(u)
        f = [[] for _ in range(51)]
        for i in range(1, 51):
            for j in range(1, 51):
                if gcd(i, j) == 1:
                    f[i].append(j)
        stks = [[] for _ in range(51)]
        ans = [-1] * n
        stk = [(0, -1, 0, 0)]
        while stk:
            i, fa, depth, k = stk[-1]
            if k == 0:
                t = mx = -1
                for v in f[nums[i]]:
                    cur = stks[v]
                    if cur and cur[-1][1] > mx:
                        t, mx = cur[-1]
                ans[i] = t
            else:
                jprev = g[i][k - 1]
                if jprev != fa:
                    stks[nums[i]].pop()
            while k < len(g[i]) and g[i][k] == fa:
                k += 1
            if k == len(g[i]):
                stk.pop()
                continue
            j = g[i][k]
            stk[-1] = (i, fa, depth, k + 1)
            stks[nums[i]].append((i, depth))
            stk.append((j, i, depth + 1, 0))
        return ans
