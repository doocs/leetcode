class Solution:
    def miceAndCheese(self, reward1: List[int], reward2: List[int], k: int) -> int:
        n = len(reward1)
        idx = sorted(range(n), key=lambda i: reward1[i] - reward2[i], reverse=True)
        return sum(reward1[i] for i in idx[:k]) + sum(reward2[i] for i in idx[k:])
