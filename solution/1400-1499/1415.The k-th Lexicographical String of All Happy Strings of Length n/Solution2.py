class Solution:
    def getHappyString(self, n: int, k: int) -> str:
        if k > 3 * (1 << (n - 1)):
            return ""
        cs = "abc"
        ans = []
        for i in range(n):
            remain = 1 << (n - i - 1)
            for c in cs:
                if ans and ans[-1] == c:
                    continue
                if k <= remain:
                    ans.append(c)
                    break
                k -= remain
        return "".join(ans)
