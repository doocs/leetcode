class Solution:
    def maxVowels(self, s: str, k: int) -> int:
        vowels = set('aeiou')
        t = sum(c in vowels for c in s[:k])
        ans = t
        for i in range(k, len(s)):
            t += s[i] in vowels
            t -= s[i - k] in vowels
            ans = max(ans, t)
        return ans
