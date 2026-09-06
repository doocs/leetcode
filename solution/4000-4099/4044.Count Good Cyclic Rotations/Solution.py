class Solution:
    def countGoodRotations(self, nums: list[int]) -> int:
        n = len(nums)
        m = n // 2
        l = sum(nums[:m])
        r = sum(nums[m:])
        ans = int(l > r)
        for i in range(n - 1):
            l -= nums[i]
            r += nums[i]
            l += nums[(i + m) % n]
            r -= nums[(i + m) % n]
            ans += int(l > r)
        return ans
