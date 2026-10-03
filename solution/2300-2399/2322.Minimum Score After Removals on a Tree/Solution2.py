class Solution:
    def minimumScore(self, nums: List[int], edges: List[List[int]]) -> int:
        n = len(nums)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = 0
        for x in nums:
            s ^= x

        def component_xor(root: int, ban: int) -> int:
            sub = [0] * n
            stk = [(root, ban, 0)]
            while stk:
                i, fa, state = stk.pop()
                if state == 0:
                    stk.append((i, fa, 1))
                    for j in g[i]:
                        if j != fa:
                            stk.append((j, i, 0))
                else:
                    res = nums[i]
                    for j in g[i]:
                        if j != fa:
                            res ^= sub[j]
                    sub[i] = res
            return sub[root]

        def collect(root: int, ban: int, s1: int) -> None:
            nonlocal ans
            sub = [0] * n
            stk = [(root, ban, 0)]
            while stk:
                i, fa, state = stk.pop()
                if state == 0:
                    stk.append((i, fa, 1))
                    for j in g[i]:
                        if j != fa:
                            stk.append((j, i, 0))
                else:
                    res = nums[i]
                    for j in g[i]:
                        if j != fa:
                            s2 = sub[j]
                            res ^= s2
                            mx = max(s ^ s1, s2, s1 ^ s2)
                            mn = min(s ^ s1, s2, s1 ^ s2)
                            ans = min(ans, mx - mn)
                    sub[i] = res

        ans = inf
        for i in range(n):
            for j in g[i]:
                s1 = component_xor(i, j)
                collect(i, j, s1)
        return ans
