class Solution:
    def incremovableSubarrayCount(self, nums: List[int]) -> int:
        i, n = 0, len(nums)
        while i + 1 < n and nums[i] < nums[i + 1]:
            i += 1
        if i == n - 1:
            return n * (n + 1) // 2
        ans = i + 2
        j = n - 1
        while j:
            while i >= 0 and nums[i] >= nums[j]:
                i -= 1
            ans += i + 2
            if nums[j - 1] >= nums[j]:
                break
            j -= 1
        return ans
