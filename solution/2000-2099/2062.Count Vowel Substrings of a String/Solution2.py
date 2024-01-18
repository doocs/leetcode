class Solution:
    def countVowelSubstrings(self, word: str) -> int:
        s = set('aeiou')
        ans, n = 0, len(word)
        for i in range(n):
            t = set()
            for c in word[i:]:
                if c not in s:
                    break
                t.add(c)
                ans += len(t) == 5
        return ans
