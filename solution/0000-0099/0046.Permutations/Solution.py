class Solution:
    def permute(self, nums: List[int]) -> List[List[int]]:
        n = len(nums)
        res = []
        path = [0] * n
        used = [False] * n

        def dfs(u):
            if u == n:
                res.append(path.copy())
                return
            for i in range(n):
                if not used[i]:
                    path[u] = nums[i]
                    used[i] = True
                    dfs(u + 1)
                    used[i] = False

        dfs(0)
        return res
