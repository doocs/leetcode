class Solution:
    def kMirror(self, k: int, n: int) -> int:
        def check(x: int, k: int) -> bool:
            s = []
            while x:
                s.append(x % k)
                x //= k
            return s == s[::-1]

        ans = 0
        for l in count(1):
            x = 10 ** ((l - 1) // 2)
            y = 10 ** ((l + 1) // 2)
            for i in range(x, y):
                v = i
                j = i if l % 2 == 0 else i // 10
                while j > 0:
                    v = v * 10 + j % 10
                    j //= 10
                if check(v, k):
                    ans += v
                    n -= 1
                    if n == 0:
                        return ans
