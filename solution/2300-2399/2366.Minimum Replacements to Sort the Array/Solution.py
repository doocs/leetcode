class Solution:
    def minimumReplacement(self, nums: List[int]) -> int:
        ans, n = 0, len(nums)
        mi = nums[-1]
        for i in range(n - 2, -1, -1):
            v = nums[i]
            if v <= mi:
                mi = v
                continue
            k = (v + mi - 1) // mi
            ans += k - 1
            mi = v // k
        return ans
