class Solution:
    def printVertically(self, s: str) -> List[str]:
        words = s.split()
        n = max(len(w) for w in words)
        ans = []
        for j in range(n):
            t = [w[j] if j < len(w) else ' ' for w in words]
            while t[-1] == ' ':
                t.pop()
            ans.append(''.join(t))
        return ans
