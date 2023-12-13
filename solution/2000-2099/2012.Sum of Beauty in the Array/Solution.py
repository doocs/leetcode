class Solution:
    def sumOfBeauties(self, nums: List[int]) -> int:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = min(right[i + 1], nums[i])
        ans = 0
        l = nums[0]
        for i in range(1, n - 1):
            r = right[i + 1]
            if l < nums[i] < r:
                ans += 2
            elif nums[i - 1] < nums[i] < nums[i + 1]:
                ans += 1
            l = max(l, nums[i])
        return ans
