class Solution:
    def maximumNumberOfStringPairs(self, words: list[str]) -> int:
        cnt = Counter()
        ans = 0
        for w in words:
            ans += cnt[w[::-1]]
            cnt[w] += 1
        return ans
