class Solution:
    def minCapability(self, nums: List[int], k: int) -> int:
        def f(x):
            cnt, j = 0, -2
            for i, v in enumerate(nums):
                if v > x or i == j + 1:
                    continue
                cnt += 1
                j = i
            return cnt >= k

        return bisect_left(range(max(nums) + 1), True, key=f)
