class Solution:
    def printVertically(self, s: str) -> List[str]:
        words = s.split()
        m, n = len(words), max(len(word) for word in words)
        ans = []
        for j in range(n):
            t = []
            for i in range(m):
                word = words[i]
                t.append(word[j] if j < len(word) else ' ')
            ans.append(''.join(t).rstrip())
        return ans
