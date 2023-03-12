class Solution:
    def findWords(self, words: List[str]) -> List[str]:
        ans = []
        s = "12210111011122000010020202"
        for w in words:
            x = s[ord(w[0].lower()) - ord('a')]
            if all(s[ord(c.lower()) - ord('a')] == x for c in w):
                ans.append(w)
        return ans
