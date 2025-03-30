class Solution:
    def longestSquareStreak(self, nums: List[int]) -> int:
        @cache
        def dfs(x: int) -> int:
            if x not in s:
                return 0
            return 1 + dfs(x * x)

        s = set(nums)
        ans = max(dfs(x) for x in s)
        return -1 if ans < 2 else ans
