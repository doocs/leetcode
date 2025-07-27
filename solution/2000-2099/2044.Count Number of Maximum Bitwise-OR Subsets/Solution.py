class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        def dfs(i, t):
            nonlocal ans, mx
            if i == len(nums):
                if t == mx:
                    ans += 1
                return
            dfs(i + 1, t)
            dfs(i + 1, t | nums[i])

        ans = 0
        mx = reduce(lambda x, y: x | y, nums)
        dfs(0, 0)
        return ans
