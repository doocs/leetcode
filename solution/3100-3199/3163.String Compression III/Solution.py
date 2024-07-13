class Solution:
    def compressedString(self, word: str) -> str:
        g = groupby(word)
        ans = []
        for c, v in g:
            k = len(list(v))
            while k:
                x = min(9, k)
                ans.append(str(x) + c)
                k -= x
        return "".join(ans)
