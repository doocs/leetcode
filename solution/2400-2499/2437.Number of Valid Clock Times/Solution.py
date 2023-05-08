class Solution:
    def countTime(self, time: str) -> int:
        def f(s: str, m: int) -> int:
            cnt = 0
            for i in range(m):
                a = s[0] == "?" or (int(s[0]) == i // 10)
                b = s[1] == "?" or (int(s[1]) == i % 10)
                cnt += a and b
            return cnt

        return f(time[:2], 24) * f(time[3:], 60)
