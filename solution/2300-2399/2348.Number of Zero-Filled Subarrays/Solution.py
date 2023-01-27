class Solution:
    def zeroFilledSubarray(self, nums: List[int]) -> int:
        ans = cnt = 0
        for v in nums:
            cnt = 0 if v else cnt + 1
            ans += cnt
        return ans
