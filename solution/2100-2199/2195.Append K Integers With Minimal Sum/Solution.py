class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        nums.append(0)
        nums.append(2 * 10**9)
        nums.sort()
        ans = 0
        for a, b in pairwise(nums):
            n = min(k, b - a - 1)
            if n <= 0:
                continue
            k -= n
            ans += (a + 1 + a + n) * n // 2
            if k == 0:
                break
        return ans
