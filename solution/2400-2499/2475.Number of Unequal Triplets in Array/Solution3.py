class Solution:
    def unequalTriplets(self, nums: list[int]) -> int:
        cnt = Counter(nums)
        n = len(nums)
        ans = a = 0
        for b in cnt.values():
            c = n - a - b
            ans += a * b * c
            a += b
        return ans
