class Solution:
    def resilientSubarray(self, nums: list[int], k: int) -> int:
        ans = cnt = 0
        for i, x in enumerate(nums):
            x %= k
            if i and nums[i - 1] % k == x:
                cnt += 1
            else:
                cnt = 1
            if x * (cnt - 1) % k == 0:
                ans = max(ans, cnt)
        return ans
