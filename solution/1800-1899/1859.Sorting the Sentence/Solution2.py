class Solution:
    def sortSentence(self, s: str) -> str:
        ws = s.split()
        ans = [None] * len(ws)
        for w in ws:
            ans[int(w[-1]) - 1] = w[:-1]
        return ' '.join(ans)
