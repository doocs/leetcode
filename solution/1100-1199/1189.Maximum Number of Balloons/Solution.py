class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        counter = Counter(text)
        counter['l'] >>= 1
        counter['o'] >>= 1
        return min(counter[c] for c in 'balon')
