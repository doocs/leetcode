class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        ans = h = 0
        for v in gain:
            h += v
            ans = max(ans, h)
        return ans
