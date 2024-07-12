class Solution:
    def maximumGain(self, s: str, x: int, y: int) -> int:
        a, b = "a", "b"
        if x < y:
            x, y = y, x
            a, b = b, a
        ans = cnt1 = cnt2 = 0
        for c in s:
            if c == a:
                cnt1 += 1
            elif c == b:
                if cnt1:
                    ans += x
                    cnt1 -= 1
                else:
                    cnt2 += 1
            else:
                ans += min(cnt1, cnt2) * y
                cnt1 = cnt2 = 0
        ans += min(cnt1, cnt2) * y
        return ans
