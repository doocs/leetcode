class Solution:
    def minMirrorPairDistance(self, nums: List[int]) -> int:
        def reverse(x: int) -> int:
            y = 0
            while x:
                v = x % 10
                y = y * 10 + v
                x //= 10
            return y

        pos = {}
        ans = inf
        for i, x in enumerate(nums):
            if x in pos:
                ans = min(ans, i - pos[x])
            pos[reverse(x)] = i
        return -1 if ans == inf else ans
