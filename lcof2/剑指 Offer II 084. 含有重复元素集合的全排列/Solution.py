class Solution:
    def permuteUnique(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        res = []
        path = [0] * n
        used = [False] * n
        nums.sort()

        def dfs(u):
            if u == n:
                res.append(path.copy())
                return
            for i in range(n):
                if used[i] or (i > 0 and nums[i] == nums[i - 1] and not used[i - 1]):
                    continue
                path[u] = nums[i]
                used[i] = True
                dfs(u + 1)
                used[i] = False

        dfs(0)
        return res
