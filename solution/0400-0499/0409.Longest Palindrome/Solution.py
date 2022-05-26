class Solution:
    def longestPalindrome(self, s: str) -> int:
        res = [0] * 128
        for ch in s:
            res[ord(ch)] += 1
        odd_cnt, n = 0, len(s)
        for e in res:
            odd_cnt += (e % 2)
        return n if odd_cnt == 0 else n - odd_cnt + 1
