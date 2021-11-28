class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = Counter(s)
        return sum(e % 2 for e in counter.values()) < 2
