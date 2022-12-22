class Solution:
    def findSubsequences(self, nums: List[int]) -> List[List[int]]:
        def dfs(u, last, t):
            if u == len(nums):
                if len(t) > 1:
                    ans.append(t[:])
                return
            if nums[u] >= last:
                t.append(nums[u])
                dfs(u + 1, nums[u], t)
                t.pop()
            if nums[u] != last:
                dfs(u + 1, last, t)

        ans = []
        dfs(0, -1000, [])
        return ans
