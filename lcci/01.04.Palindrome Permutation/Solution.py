class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = Counter(s)
        return sum(1 for v in counter.values() if v % 2 == 1) <= 1
