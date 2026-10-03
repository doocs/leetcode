class Solution:
    def componentValue(self, nums: List[int], edges: List[List[int]]) -> int:
        def check(t: int) -> bool:
            sz = [0] * n
            stk = [(0, -1, 0)]
            while stk:
                i, fa, state = stk.pop()
                if state == 0:
                    stk.append((i, fa, 1))
                    for j in g[i]:
                        if j != fa:
                            stk.append((j, i, 0))
                else:
                    x = nums[i]
                    for j in g[i]:
                        if j != fa:
                            x += sz[j]
                    if x > t:
                        return False
                    sz[i] = 0 if x == t else x
            return sz[0] == 0

        n = len(nums)
        g = [[] for _ in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)
        s = sum(nums)
        mx = max(nums)
        for k in range(min(n, s // mx), 1, -1):
            if s % k == 0 and check(s // k):
                return k - 1
        return 0
