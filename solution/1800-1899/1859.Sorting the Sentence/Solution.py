class Solution:
    def sortSentence(self, s: str) -> str:
        ws = [(w[:-1], int(w[-1])) for w in s.split()]
        ws.sort(key=lambda x: x[1])
        return ' '.join(w for w, _ in ws)
