class Solution:
    def compressString(self, S: str) -> str:
        t = "".join(a + str(len(list(b))) for a, b in groupby(S))
        return min(S, t, key=len)
