class Solution:
    def subsetsWithDup(self, nums: List[int]) -> List[List[int]]:
        def dfs(nums, i, res, path):
            res.append(copy.deepcopy(path))
            for j in range(i, len(nums)):
                if j != i and nums[j] == nums[j - 1]:
                    continue
                path.append(nums[j])
                dfs(nums, j + 1, res, path)
                path.pop()

        res, path = [], []
        nums.sort()
        dfs(nums, 0, res, path)
        return res
