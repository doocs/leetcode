class Solution:
    def maximumJumps(self, nums: List[int], target: int) -> int:
        @cache
        def dfs(i: int) -> int:
            if i == n - 1:
                return 0
            ans = -inf
            for j in range(i + 1, n):
                if abs(nums[i] - nums[j]) <= target:
                    ans = max(ans, 1 + dfs(j))
            return ans

        n = len(nums)
        ans = dfs(0)
        return -1 if ans < 0 else ans
