class Solution:
    def topKFrequent(self, words: list[str], k: int) -> list[str]:
        cnt = Counter(words)
        return sorted(cnt, key=lambda x: (-cnt[x], x))[:k]
