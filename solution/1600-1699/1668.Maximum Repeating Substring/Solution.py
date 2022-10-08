class Solution:
    def maxRepeating(self, sequence: str, word: str) -> int:
        x = len(sequence) // len(word)
        for k in range(x, 0, -1):
            if word * k in sequence:
                return k
        return 0
