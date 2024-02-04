class Solution:
    def minimumTimeToInitialState(self, word: str, k: int) -> int:
        n = len(word)
        for i in range(1, 10001):
            re = i * k
            if re >= n:
                return i
            if word[re:] == word[:n - re]:
                return i
        return 0
