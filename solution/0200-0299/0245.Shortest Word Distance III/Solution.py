class Solution:
    def shortestWordDistance(self, wordsDict: List[str], word1: str, word2: str) -> int:
        i1 = i2 = -1
        shortest_distance = len(wordsDict)
        same = word1 == word2
        for i in range(len(wordsDict)):
            if same:
                if word1 == wordsDict[i]:
                    if i1 != -1:
                        shortest_distance = min(shortest_distance, i - i1)
                    i1 = i
            else:
                if word1 == wordsDict[i]:
                    i1 = i
                if word2 == wordsDict[i]:
                    i2 = i
                if i1 != -1 and i2 != -1:
                    shortest_distance = min(shortest_distance, abs(i1 - i2))
        return shortest_distance
