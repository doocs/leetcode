class Solution:
    def maxProduct(self, words: List[str]) -> int:
        n = len(words)
        masks = [0] * n
        for i, word in enumerate(words):
            for c in word:
                masks[i] |= (1 << (ord(c) - ord('a')))
        ans = 0
        for i in range(n - 1):
            for j in range(i + 1, n):
                if (masks[i] & masks[j]) == 0:
                    ans = max(ans, len(words[i]) * len(words[j]))
        return ans
