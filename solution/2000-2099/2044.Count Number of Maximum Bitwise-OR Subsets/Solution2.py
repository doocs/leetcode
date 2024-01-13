class Solution:
    def countMaxOrSubsets(self, nums: List[int]) -> int:
        def dfs(u, t):
            nonlocal ans, mx
            if u == len(nums):
                if t > mx:
                    mx, ans = t, 1
                elif t == mx:
                    ans += 1
                return
            dfs(u + 1, t | nums[u])
            dfs(u + 1, t)

        ans = mx = 0
        dfs(0, 0)
        return ans
