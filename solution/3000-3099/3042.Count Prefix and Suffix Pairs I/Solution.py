class Solution:
    def countPrefixSuffixPairs(self, words: List[str]) -> int:
        ans = 0
        for i, s in enumerate(words):
            for t in words[i + 1 :]:
                ans += t.endswith(s) and t.startswith(s)
        return ans
