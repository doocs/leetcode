class Solution:
    def minRotations(self, s: str) -> int:
        ans = pre = 0
        for cur in map(int, s):
            diff = abs(cur - pre)
            ans += min(diff, 10 - diff)
            pre = cur
        return ans
