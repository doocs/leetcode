class Solution:
    def freqAlphabets(self, s: str) -> str:
        ans = []
        i, n = 0, len(s)
        while i < n:
            if i + 2 < n and s[i + 2] == "#":
                ans.append(chr(int(s[i : i + 2]) + ord("a") - 1))
                i += 3
            else:
                ans.append(chr(int(s[i]) + ord("a") - 1))
                i += 1
        return "".join(ans)
