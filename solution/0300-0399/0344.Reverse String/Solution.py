class Solution:
    def generatePossibleNextMoves(self, s: str) -> List[str]:
        if not s or len(s) < 2:
            return []
        n = len(s)
        res = []
        for i in range(n - 1):
            if s[i] == '+' and s[i + 1] == '+':
                res.append(s[:i] + "--" + s[i + 2:])
        return res
