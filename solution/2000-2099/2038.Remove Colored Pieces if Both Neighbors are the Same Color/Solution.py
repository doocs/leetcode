class Solution:
    def winnerOfGame(self, colors: str) -> bool:
        a = b = 0
        cnt1 = cnt2 = 0
        for c in colors:
            if c == 'A':
                a += 1
                if a > 2:
                    cnt1 += 1
                b = 0
            else:
                b += 1
                if b > 2:
                    cnt2 += 1
                a = 0
        return cnt1 > cnt2
