class Solution:
    def commonChars(self, words: List[str]) -> List[str]:
        freq = [10000] * 26
        for word in words:
            t = [0] * 26
            for c in word:
                t[ord(c) - ord('a')] += 1
            for i in range(26):
                freq[i] = min(freq[i], t[i])
        res = []
        for i in range(26):
            if freq[i] > 0:
                res.extend([chr(i + ord("a"))] * freq[i])
        return res
