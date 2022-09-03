class Solution:
    def PredictTheWinner(self, nums: List[int]) -> bool:
        @cache
        def dfs(i, j):
            if i > j:
                return 0
            a = min(dfs(i + 1, j), dfs(i, j - 1))
            return s[j + 1] - s[i] - a

        s = list(accumulate(nums, initial=0))
        return dfs(0, len(nums) - 1) * 2 >= s[-1]
