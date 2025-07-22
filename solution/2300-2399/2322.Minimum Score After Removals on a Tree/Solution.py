class Solution:
    def minimumScore(self, nums: List[int], edges: List[List[int]]) -> int:
        def dfs(i: int, fa: int) -> int:
            res = nums[i]
            for j in g[i]:
                if j != fa:
                    res ^= dfs(j, i)
            return res

        def dfs2(i: int, fa: int) -> int:
            nonlocal s, s1, ans
            res = nums[i]
            for j in g[i]:
                if j != fa:
                    s2 = dfs2(j, i)
                    res ^= s2
                    mx = max(s ^ s1, s2, s1 ^ s2)
                    mn = min(s ^ s1, s2, s1 ^ s2)
                    ans = min(ans, mx - mn)
            return res

        g = defaultdict(list)
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = reduce(lambda x, y: x ^ y, nums)
        n = len(nums)
        ans = inf
        for i in range(n):
            for j in g[i]:
                s1 = dfs(i, j)
                dfs2(i, j)
        return ans
