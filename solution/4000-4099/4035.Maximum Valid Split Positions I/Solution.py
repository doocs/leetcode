class Solution:
    def maxValidSplits(self, nums: List[int]) -> int:
        def calc(arr: List[int]) -> int:
            m = len(arr)
            pre = list(accumulate(arr, gcd))
            suf = list(accumulate(arr[::-1], gcd))[::-1]
            return sum(pre[i] == suf[i + 1] for i in range(m - 1))

        ans = calc(nums)
        for i in range(len(nums)):
            ans = max(ans, calc(nums[:i] + nums[i + 1 :]))
        return ans
