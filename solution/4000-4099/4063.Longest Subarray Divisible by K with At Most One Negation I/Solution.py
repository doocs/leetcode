class Solution:
    def longestSubarray(self, nums: list[int], k: int) -> int:
        def f(nums: list[int], k: int) -> int:
            d = {0: -1}
            s = res = 0
            for i, x in enumerate(nums):
                s = (s + x) % k
                if s in d:
                    res = max(res, i - d[s])
                else:
                    d[s] = i
            return res

        ans = f(nums, k)
        for i, x in enumerate(nums):
            nums[i] = -x
            ans = max(ans, f(nums, k))
            nums[i] = x
        return ans
