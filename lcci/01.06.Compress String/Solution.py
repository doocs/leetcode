class Solution:
    def compressString(self, S: str) -> str:
        if len(S) < 2:
            return S
        p, q = 0, 1
        res = ''
        while q < len(S):
            if S[p] != S[q]:
                res += S[p] + str(q - p)
                p = q
            q += 1
        res += S[p] + str(q - p)
        return res if len(res) < len(S) else S
