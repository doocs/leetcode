class Solution:
    def purchasePlans(self, nums: List[int], target: int) -> int:
        nums.sort()
        i, j = 0, len(nums) - 1
        res = 0
        while i < j:
            if nums[i] + nums[j] > target:
                j -= 1
            else:
                res += j - i
                i += 1
        return res % 1000000007
