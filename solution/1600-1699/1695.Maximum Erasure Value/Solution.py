class Solution:
    def maximumUniqueSubarray(self, nums: List[int]) -> int:
        d = [0] * (max(nums) + 1)
        s = list(accumulate(nums, initial=0))
        ans = j = 0
        for i, v in enumerate(nums, 1):
            j = max(j, d[v])
            ans = max(ans, s[i] - s[j])
            d[v] = i
        return ans
