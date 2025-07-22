class Solution:
    def concatHex36(self, n: int) -> str:
        def f(x: int, k: int) -> str:
            res = []
            while x:
                v = x % k
                if v <= 9:
                    res.append(str(v))
                else:
                    res.append(chr(ord("A") + v - 10))
                x //= k
            return "".join(res[::-1])

        x, y = n**2, n**3
        return f(x, 16) + f(y, 36)
