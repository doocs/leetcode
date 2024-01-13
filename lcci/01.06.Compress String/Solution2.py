class Solution:
    def compressString(self, S: str) -> str:
        t = []
        i, n = 0, len(S)
        while i < n:
            j = i + 1
            while j < n and S[j] == S[i]:
                j += 1
            t.append(S[i] + str(j - i))
            i = j
        return min(S, "".join(t), key=len)
