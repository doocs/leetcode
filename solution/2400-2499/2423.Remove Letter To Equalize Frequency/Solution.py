class Solution:
    def equalFrequency(self, word: str) -> bool:
        for i in range(len(word)):
            cnt = Counter(word[:i] + word[i + 1 :])
            if len(set(cnt.values())) == 1:
                return True
        return False
