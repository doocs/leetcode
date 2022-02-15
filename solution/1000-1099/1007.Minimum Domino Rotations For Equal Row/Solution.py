class Solution:
    def minDominoRotations(self, A: List[int], B: List[int]) -> int:
        a, b = A[0], B[0]
        c, d = b, a
        counta, countb = 0, 0
        countc, countd = 1, 1
        for ai, bi in zip(A[1:], B[1:]):
            if ai == a:
                pass
            elif ai != a and bi == a:
                counta += 1
            else:
                counta = -30000
            if bi == b:
                pass
            elif bi != b and ai == b:
                countb += 1
            else:
                countb = -30000
            if ai == c:
                pass
            elif ai != c and bi == c:
                countc += 1
            else:
                countc = -30000
            if bi == d:
                pass
            elif bi != d and ai == d:
                countd += 1
            else:
                countd = -30000
        if counta < 0 and countb < 0 and countc < 0 and countd < 0:
            return -1
        else:
            ans = 30000
            for count in [counta, countb, countc, countd]:
                if count >= 0:
                    ans = min(ans, count)
            return ans
