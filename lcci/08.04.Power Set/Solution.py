class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def dfs(u, t):
            if u == len(nums):
                ans.append(t[:])
                return
            t.append(nums[u])
            dfs(u + 1, t)
            t.pop()
            dfs(u + 1, t)

        ans = []
        dfs(0, [])
        return ans
