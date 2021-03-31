class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        def dfs(nums, i, res, path):
            res.append(copy.deepcopy(path))
            while i < len(nums):
                path.append(nums[i])
                dfs(nums, i + 1, res, path)
                path.pop()
                i += 1
        res, path = [], []
        dfs(nums, 0, res, path)
        return res
