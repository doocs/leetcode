class Solution:
    def canPermutePalindrome(self, s: str) -> bool:
        counter = collections.Counter(s)
        odd_cnt = sum(e % 2 for e in counter.values())
        return odd_cnt < 2
