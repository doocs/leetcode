class Solution:
    def minMaxGame(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1:
            return nums[0]
        t = []
        for i in range(n >> 1):
            v = (
                max(nums[i << 1], nums[i << 1 | 1])
                if i & 1
                else min(nums[i << 1], nums[i << 1 | 1])
            )
            t.append(v)
        return self.minMaxGame(t)
