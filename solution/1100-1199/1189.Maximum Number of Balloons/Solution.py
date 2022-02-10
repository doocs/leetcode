class Solution:
    def maxNumberOfBalloons(self, text: str) -> int:
        ans = 0
        counter = Counter(text)
        counter['l'] >>= 1
        counter['o'] >>= 1
        return min(counter['b'], counter['a'], counter['l'], counter['o'], counter['n'])
