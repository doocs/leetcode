class Solution:
    def minZeroArray(self, nums: List[int], queries: List[List[int]]) -> int:
        def check(k: int) -> bool:
            d = [0] * (len(nums) + 1)
            for l, r, val in queries[:k]:
                d[l] += val
                d[r + 1] -= val
            s = 0
            for x, y in zip(nums, d):
                s += y
                if x > s:
                    return False
            return True

        m = len(queries)
        l = bisect_left(range(m + 1), True, key=check)
        return -1 if l > m else l
