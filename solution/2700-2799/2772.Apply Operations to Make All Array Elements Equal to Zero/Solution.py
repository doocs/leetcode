class Solution:
    def checkArray(self, nums: List[int], k: int) -> bool:
        n = len(nums)
        d = [0] * (n + 1)
        s = 0
        for i, x in enumerate(nums):
            s += d[i]
            x += s
            if x == 0:
                continue
            if x < 0 or i + k > n:
                return False
            s -= x
            d[i + k] += x
        return True
