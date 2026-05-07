mx = 10**6 + 1
factors = [[] for _ in range(mx)]
for i in range(2, mx):
    if not factors[i]:
        for j in range(i, mx, i):
            factors[j].append(i)


class Solution:
    def minJumps(self, nums: List[int]) -> int:
        n = len(nums)
        g = defaultdict(list)
        for i, x in enumerate(nums):
            for p in factors[x]:
                g[p].append(i)
        ans = 0
        vis = [False] * n
        vis[0] = True
        q = [0]
        while 1:
            nq = []
            for i in q:
                if i == n - 1:
                    return ans
                idx = g[nums[i]]
                idx.append(i + 1)
                if i:
                    idx.append(i - 1)
                for j in idx:
                    if not vis[j]:
                        vis[j] = True
                        nq.append(j)
                idx.clear()
            q = nq
            ans += 1
