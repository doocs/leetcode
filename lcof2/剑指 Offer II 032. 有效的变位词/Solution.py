class Solution:
    def isAnagram(self, s: str, t: str) -> bool:
        if len(s) != len(t) or s == t:
            return False
        return Counter(s) == Counter(t)
