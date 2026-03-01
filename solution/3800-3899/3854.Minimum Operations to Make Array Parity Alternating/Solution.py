class Solution:
    def makeParityAlternating(self, nums: List[int]) -> List[int]:
        def f(k: int) -> List[int]:
            cnt = 0
            a, b = inf, -inf
            for i, x in enumerate(nums):
                if ((x - i) & 1) != k:
                    cnt += 1
                    if x == mn:
                        x += 1
                    elif x == mx:
                        x -= 1
                a = min(a, x)
                b = max(b, x)
            return [cnt, max(1, b - a)]

        if len(nums) == 1:
            return [0, 0]

        mn, mx = min(nums), max(nums)
        return min(f(0), f(1))
