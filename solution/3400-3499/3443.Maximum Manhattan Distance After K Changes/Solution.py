class Solution:
    def maxDistance(self, s: str, k: int) -> int:
        def calc(a: str, b: str) -> int:
            ans = mx = cnt = 0
            for c in s:
                if c == a or c == b:
                    mx += 1
                elif cnt < k:
                    cnt += 1
                    mx += 1
                else:
                    mx -= 1
                ans = max(ans, mx)
            return ans

        a = calc("S", "E")
        b = calc("S", "W")
        c = calc("N", "E")
        d = calc("N", "W")
        return max(a, b, c, d)
