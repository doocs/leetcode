class Solution:
    def licenseKeyFormatting(self, s: str, k: int) -> str:
        n = len(s)
        cnt = (n - s.count("-")) % k or k
        ans = []
        for i, c in enumerate(s):
            if c == "-":
                continue
            ans.append(c.upper())
            cnt -= 1
            if cnt == 0:
                cnt = k
                if i != n - 1:
                    ans.append("-")
        return "".join(ans).rstrip("-")
