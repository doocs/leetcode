class Solution:
    def maximumSum(self, nums: List[int]) -> int:
        nums.sort()
        g = [[] for _ in range(3)]
        for x in nums:
            g[x % 3].append(x)
        ans = 0
        for a in range(3):
            if g[a]:
                x = g[a].pop()
                for b in range(3):
                    if g[b]:
                        y = g[b].pop()
                        c = (3 - (a + b) % 3) % 3
                        if g[c]:
                            z = g[c][-1]
                            ans = max(ans, x + y + z)
                        g[b].append(y)
                g[a].append(x)
        return ans
