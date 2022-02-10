class Solution:
    def minNumberOfFrogs(self, croakOfFrogs: str) -> int:
        c = r = o = a = k = ans = 0
        for ch in croakOfFrogs:
            if ch == 'c':
                c += 1
                if k > 0:
                    k -= 1
                else:
                    ans += 1
            elif ch == 'r':
                r += 1
                c -= 1
            elif ch == 'o':
                o += 1
                r -= 1
            elif ch == 'a':
                a += 1
                o -= 1
            else:
                k += 1
                a -= 1
            if c < 0 or r < 0 or o < 0 or a < 0:
                return -1
        return -1 if c != 0 or r != 0 or o != 0 or a != 0 else ans
