class Solution:
    def countSubstrings(self, s: str, c: str) -> int:
        cnt = s.count(c)
        return cnt + cnt * (cnt - 1) // 2
