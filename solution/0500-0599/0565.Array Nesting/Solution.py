class Solution:
    def arrayNesting(self, nums: List[int]) -> int:
        n = len(nums)
        vis = [False] * n
        res = 0
        for i in range(n):
            if vis[i]:
                continue
            cur, m = nums[i], 1
            vis[cur] = True
            while nums[cur] != nums[i]:
                cur = nums[cur]
                m += 1
                vis[cur] = True
            res = max(res, m)
        return res
