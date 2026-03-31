class Solution:
    def generateString(self, s: str, t: str) -> str:
        n, m = len(s), len(t)
        ans = ["a"] * (n + m - 1)
        fixed = [False] * (n + m - 1)

        for i, b in enumerate(s):
            if b != "T":
                continue
            for j, c in enumerate(t):
                k = i + j
                if fixed[k] and ans[k] != c:
                    return ""
                ans[k] = c
                fixed[k] = True

        for i, b in enumerate(s):
            if b != "F":
                continue
            if "".join(ans[i : i + m]) != t:
                continue
            for j in range(i + m - 1, i - 1, -1):
                if not fixed[j]:
                    ans[j] = "b"
                    break
            else:
                return ""

        return "".join(ans)
