class Solution:
    def areSentencesSimilar(self, sentence1: str, sentence2: str) -> bool:
        if sentence1 == sentence2:
            return True
        n1, n2 = len(sentence1), len(sentence2)
        if n1 == n2:
            return False
        if n1 < n2:
            sentence1, sentence2 = sentence2, sentence1
        words1, words2 = sentence1.split(), sentence2.split()
        i = j = 0
        while i < len(words2) and words1[i] == words2[i]:
            i += 1
        if i == len(words2):
            return True
        while j < len(words2) and words1[len(words1) - 1 - j] == words2[len(words2) - 1 - j]:
            j += 1
        if j == len(words2):
            return True
        return i + j == len(words2)
