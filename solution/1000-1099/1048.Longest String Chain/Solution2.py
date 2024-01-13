class Solution:
    def longestStrChain(self, words: List[str]) -> int:
        words.sort(key=lambda x: len(x))
        res = 0
        mp = {}
        for word in words:
            x = 1
            for i in range(len(word)):
                pre = word[:i] + word[i + 1 :]
                x = max(x, mp.get(pre, 0) + 1)
            mp[word] = x
            res = max(res, x)
        return res
