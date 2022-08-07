class WordDistance:
    def __init__(self, wordsDict: List[str]):
        self.words = {}
        for i, word in enumerate(wordsDict):
            indexes = self.words.get(word, [])
            indexes.append(i)
            self.words[word] = indexes

    def shortest(self, word1: str, word2: str) -> int:
        idx1, idx2 = self.words[word1], self.words[word2]
        i1 = i2 = 0
        shortest = inf
        while i1 < len(idx1) and i2 < len(idx2):
            shortest = min(shortest, abs(idx1[i1] - idx2[i2]))
            smaller = idx1[i1] < idx2[i2]
            if smaller:
                i1 += 1
            else:
                i2 += 1
        return shortest


# Your WordDistance object will be instantiated and called as such:
# obj = WordDistance(wordsDict)
# param_1 = obj.shortest(word1,word2)
