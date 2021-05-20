class Solution:
    def topKFrequent(self, words: List[str], k: int) -> List[str]:
        counter = collections.Counter(words)
        res = sorted(counter, key=lambda word: (-counter[word], word))
        return res[:k]
