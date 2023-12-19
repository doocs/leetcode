class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        def dfs(i: int, s: int):
            nonlocal ans
            if i >= len(nums):
                ans += s
                return
            dfs(i + 1, s)
            dfs(i + 1, s ^ nums[i])

        ans = 0
        dfs(0, 0)
        return ans
