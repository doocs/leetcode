class Solution:
    def hasMatch(self, s: str, p: str) -> bool:
        i = 0
        for t in p.split("*"):
            j = s.find(t, i)
            if j == -1:
                return False
            i = j + len(t)
        return True
