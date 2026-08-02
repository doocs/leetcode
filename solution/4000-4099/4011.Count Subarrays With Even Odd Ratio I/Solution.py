class Solution:
    def countRatioSubarrays(self, nums: list[int], a: int, b: int) -> int:
        ans = 0
        n = len(nums)
        for i in range(n):
            y = 0
            for j in range(i, n):
                y += nums[j] % 2
                x = j - i + 1 - y
                if y and (x / y) <= (a / b):
                    ans += 1
        return ans
