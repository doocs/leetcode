class Solution:
    def maxProduct(self, words: List[str]) -> int:
        mask = [0] * len(words)
        for i, w in enumerate(words):
            for c in w:
                mask[i] |= 1 << (ord(c) - ord("a"))
        ans = 0
        for i, a in enumerate(words):
            for j, b in enumerate(words[i + 1 :], i + 1):
                if (mask[i] & mask[j]) == 0:
                    ans = max(ans, len(a) * len(b))
        return ans
