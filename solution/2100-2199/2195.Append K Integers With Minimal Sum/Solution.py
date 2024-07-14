class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        nums.extend([0, 2 * 10**9])
        nums.sort()
        ans = 0
        for a, b in pairwise(nums):
            m = max(0, min(k, b - a - 1))
            ans += (a + 1 + a + m) * m // 2
            k -= m
        return ans
