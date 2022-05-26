class Solution:
    def longestPalindrome(self, s: str) -> int:
        n = len(s)
        counter = Counter(s)
        odd_cnt = sum(e % 2 for e in counter.values())
        return n if odd_cnt == 0 else n - odd_cnt + 1
