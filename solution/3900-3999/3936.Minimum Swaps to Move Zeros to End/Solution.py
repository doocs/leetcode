class Solution:
    def minimumSwaps(self, nums: list[int]) -> int:
        ans = 0
        n = len(nums)
        i, j = 0, n - 1
        while i < j:
            while i < n and nums[i] != 0:
                i += 1
            while j and nums[j] == 0:
                j -= 1
            if i >= j:
                break
            ans += 1
            i += 1
            j -= 1
        return ans
