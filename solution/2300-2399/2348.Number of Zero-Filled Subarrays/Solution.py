class Solution:
    def zeroFilledSubarray(self, nums: List[int]) -> int:
        ans = 0
        cnt = 0
        for v in nums:
            if v == 0:
                cnt += 1
            else:
                ans += (1 + cnt) * cnt // 2
                cnt = 0
        ans += (1 + cnt) * cnt // 2
        return ans
