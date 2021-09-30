class Solution:
    def triangleNumber(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        ans = 0
        for i in range(n - 2):
            for j in range(i + 1, n - 1):
                left, right = j + 1, n
                while left < right:
                    mid = left + (right - left) // 2
                    if nums[mid] < nums[i] + nums[j]:
                        left = mid + 1
                    else:
                        right = mid
                ans += left - j - 1
        return ans
