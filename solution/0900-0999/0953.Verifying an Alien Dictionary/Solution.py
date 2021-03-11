class Solution:
    def isAlienSorted(self, words: List[str], order: str) -> bool:
        index = {v: k for k, v in enumerate(order)}
        for i in range(len(words) - 1):
            word1, word2 = words[i], words[i + 1]
            len1, len2 = len(word1), len(word2)
            flag = True
            for j in range(min(len1, len2)):
                diff = index[word1[j]] - index[word2[j]]
                if diff > 0:
                    return False
                if diff < 0:
                    flag = False
                    break
            if flag and len1 > len2:
                return False
        return True
