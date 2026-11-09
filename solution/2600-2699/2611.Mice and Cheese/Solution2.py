class Solution:
    def miceAndCheese(self, reward1: list[int], reward2: list[int], k: int) -> int:
        for i, x in enumerate(reward2):
            reward1[i] -= x
        reward1.sort(reverse=True)
        return sum(reward2) + sum(reward1[:k])
