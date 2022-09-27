class Solution:
    def CheckPermutation(self, s1: str, s2: str) -> bool:
        return Counter(s1) == Counter(s2)
