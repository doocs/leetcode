class Solution:
    def minimumK(self, nums: List[int]) -> int:
        def check(k: int) -> bool:
            t = 0
            for x in nums:
                t += (x + k - 1) // k
            return t <= k * k

        l, r = 1, 10**5
        while l < r:
            mid = (l + r) >> 1
            if check(mid):
                r = mid
            else:
                l = mid + 1
        return l
