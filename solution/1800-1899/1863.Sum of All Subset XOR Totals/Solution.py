class Solution:
    def subsetXORSum(self, nums: List[int]) -> int:
        def dfs(nums, depth, prev):
            self.res += prev
            for num in nums[depth:]:
                prev ^= num
                depth += 1
                dfs(nums, depth, prev)
                prev ^= num

        self.res = 0
        dfs(nums, 0, 0)
        return self.res
