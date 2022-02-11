class Solution:
    def checkAlmostEquivalent(self, word1: str, word2: str) -> bool:
        counter = defaultdict(int)
        for c in word1:
            counter[c] += 1
        for c in word2:
            counter[c] -= 1
        return all(abs(x) <= 3 for x in counter.values())
