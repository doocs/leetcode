class Solution:
    def numberOfRounds(self, loginTime: str, logoutTime: str) -> int:
        def f(s: str) -> int:
            return int(s[:2]) * 60 + int(s[3:])

        a, b = f(loginTime), f(logoutTime)
        if a > b:
            b += 1440
        a, b = (a + 14) // 15, b // 15
        return max(0, b - a)
