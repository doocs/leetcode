class Solution:
    def checkZeroOnes(self, s: str) -> bool:
        def f(x: str) -> int:
            cnt = mx = 0
            for c in s:
                if c == x:
                    cnt += 1
                    mx = max(mx, cnt)
                else:
                    cnt = 0
            return mx

        return f("1") > f("0")
