class Solution:
    def convertNumber(self, s: str) -> str:
        d = [
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine",
        ]
        i, n = 0, len(s)
        ans = []
        while i < n:
            for j, t in enumerate(d):
                m = len(t)
                if i + m <= n and s[i : i + m] == t:
                    ans.append(str(j))
                    i += m - 1
                    break
            i += 1
        return "".join(ans)
