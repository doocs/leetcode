class Solution:
    def minimumReplacement(self, nums: List[int]) -> int:
        ans = 0
        n = len(nums)
        mx = nums[-1]
        for i in range(n - 2, -1, -1):
            if nums[i] <= mx:
                mx = nums[i]
                continue
            k = (nums[i] + mx - 1) // mx
            ans += k - 1
            mx = nums[i] // k
        return ans
