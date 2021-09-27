class Solution:
    def numDecodings(self, s: str) -> int:
        mod = int(1e9 + 7)
        n = len(s)

        # dp[i - 2], dp[i - 1], dp[i]
        a, b, c = 0, 1, 0
        for i in range(1, n + 1):
            # 1 digit
            if s[i - 1] == "*":
                c = 9 * b % mod
            elif s[i - 1] != "0":
                c = b
            else:
                c = 0

            # 2 digits
            if i > 1:
                if s[i - 2] == "*" and s[i - 1] == "*":
                    c = (c + 15 * a) % mod
                elif s[i - 2] == "*":
                    if s[i - 1] > "6":
                        c = (c + a) % mod
                    else:
                        c = (c + 2 * a) % mod
                elif s[i - 1] == "*":
                    if s[i - 2] == "1":
                        c = (c + 9 * a) % mod
                    elif s[i - 2] == "2":
                        c = (c + 6 * a) % mod
                elif (
                    s[i - 2] != "0"
                    and (ord(s[i - 2]) - ord("0")) * 10 + ord(s[i - 1]) - ord("0") <= 26
                ):
                    c = (c + a) % mod

            a, b = b, c

        return c
