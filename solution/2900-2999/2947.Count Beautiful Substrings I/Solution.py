class Solution:
    def beautifulSubstrings(self, s: str, k: int) -> int:
        n = len(s)
        vs = set("aeiou")
        ans = 0
        for i in range(n):
            vowels = 0
            for j in range(i, n):
                vowels += s[j] in vs
                consonants = j - i + 1 - vowels
                if vowels == consonants and vowels * consonants % k == 0:
                    ans += 1
        return ans
