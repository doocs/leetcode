class Solution:
    def areSentencesSimilar(self, sentence1: List[str], sentence2: List[str], similarPairs: List[List[str]]) -> bool:
        if len(sentence1) != len(sentence2):
            return False
        pairs = {(word1, word2) for word1, word2 in similarPairs}
        for i in range(len(sentence1)):
            similar = (sentence1[i], sentence2[i]) in pairs or (sentence2[i], sentence1[i]) in pairs or sentence1[i] == sentence2[i]
            if not similar:
                return False
        return True
