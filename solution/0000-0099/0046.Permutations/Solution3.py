class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        def dfs(i):
            nonlocal mask
            if i == n:
                ans.append(t[:])
                return
            for j in range(n):
                if (mask >> j & 1) == 0:
                    mask |= 1 << j
                    t[i] = nums[j]
                    dfs(i + 1)
                    mask ^= 1 << j

        n = len(nums)
        mask = 0
        t = [0] * n
        ans = []
        dfs(0)
        return ans
