class Solution:
    def findScore(self, nums: List[int]) -> int:
        n = len(nums)
        vis = [False] * (n + 2)
        idx = sorted(range(n), key=lambda i: (nums[i], i))
        ans = 0
        for i in idx:
            if not vis[i + 1]:
                ans += nums[i]
                vis[i] = vis[i + 2] = True
        return ans
