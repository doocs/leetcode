class Solution:
    def secondsToRemoveOccurrences(self, s: str) -> int:
        ans = 0
        while s.count('01'):
            s = s.replace('01', '10')
            ans += 1
        return ans
