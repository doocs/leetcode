class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        return sum(v % 2 for v in Counter(s).values()) <= 1
