class Solution:
    def maxScore(self, nums: List[int]) -> int:
        @cache
        def dfs(i: int) -> int:
            return max(
                [(j - i) * nums[j] + dfs(j) for j in range(i + 1, len(nums))] or [0]
            )

        return dfs(0)
