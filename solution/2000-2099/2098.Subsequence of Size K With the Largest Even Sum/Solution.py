class Solution:
    def largestEvenSum(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = sum(nums[-k:])
        if ans % 2 == 0:
            return ans
        n = len(nums)
        mx1 = mx2 = -inf
        for x in nums[: n - k]:
            if x & 1:
                mx1 = x
            else:
                mx2 = x
        mi1 = mi2 = inf
        for x in nums[-k:][::-1]:
            if x & 1:
                mi2 = x
            else:
                mi1 = x
        ans = max(ans - mi1 + mx1, ans - mi2 + mx2, -1)
        return -1 if ans % 2 else ans
