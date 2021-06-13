class Solution:
    def makeEqual(self, words: List[str]) -> bool:
        counter = collections.Counter()
        for word in words:
            for c in word:
                counter[c] += 1
        n = len(words)
        for count in counter.values():
            if count % n != 0:
                return False
        return True
