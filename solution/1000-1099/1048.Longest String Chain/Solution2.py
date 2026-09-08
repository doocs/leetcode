class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        words.sort(key=len)
        f = {}
        ans = 0
        for w in words:
            x = 1
            for i in range(len(w)):
                pred = w[:i] + w[i + 1 :]
                x = max(x, f.get(pred, 0) + 1)
            f[w] = x
            ans = max(ans, x)
        return ans
