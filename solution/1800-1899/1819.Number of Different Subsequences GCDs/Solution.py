class Solution:
    def countDifferentSubsequenceGCDs(self, nums: List[int]) -> int:
        mx = max(nums)
        vis = set(nums)
        ans = 0
        for x in range(1, mx + 1):
            g = 0
            for y in range(x, mx + 1, x):
                if y in vis:
                    g = gcd(g, y)
                    if g == x:
                        ans += 1
                        break
        return ans
