class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        m = {0: -1}
        ans, sum = 0, 0
        for i, num in enumerate(nums):
            sum += 1 if num == 1 else -1
            if sum in m:
                ans = max(ans, i - m[sum])
            else:
                m[sum] = i
        return ans
