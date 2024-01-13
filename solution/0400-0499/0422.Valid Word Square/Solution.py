class Solution:
    def validWordSquare(self, words: List[str]) -> bool:
        m = len(words)
        n = max(len(w) for w in words)
        if m != n:
            return False
        for j in range(n):
            if words[j] != "".join(w[j] for w in words if j < len(w)):
                return False
        return True
