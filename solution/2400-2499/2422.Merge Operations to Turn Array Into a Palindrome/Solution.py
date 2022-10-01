class Solution:
    def minimumOperations(self, nums: List[int]) -> int:
        i, j = 0, len(nums) - 1
        a, b = nums[i], nums[j]
        ans = 0
        while i < j:
            if a < b:
                i += 1
                a += nums[i]
                ans += 1
            elif b < a:
                j -= 1
                b += nums[j]
                ans += 1
            else:
                i, j = i + 1, j - 1
                a, b = nums[i], nums[j]
        return ans
