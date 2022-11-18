class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        ans = s = 0
        for v in gain:
            s += v
            ans = max(ans, s)
        return ans
