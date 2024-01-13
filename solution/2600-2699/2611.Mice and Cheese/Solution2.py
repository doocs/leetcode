class Solution:
    def miceAndCheese(self, reward1: List[int], reward2: List[int], k: int) -> int:
        for i, x in enumerate(reward2):
            reward1[i] -= x
        reward1.sort(reverse=True)
        return sum(reward2) + sum(reward1[:k])
