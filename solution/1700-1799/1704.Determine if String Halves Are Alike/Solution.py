class Solution:
    def halvesAreAlike(self, s: str) -> bool:
        cnt, n = 0, len(s) >> 1
        vowels = set('aeiouAEIOU')
        for i in range(n):
            cnt += s[i] in vowels
            cnt -= s[i + n] in vowels
        return cnt == 0
