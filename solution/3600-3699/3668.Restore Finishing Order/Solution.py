class Solution:
    def recoverOrder(self, order: list[int], friends: list[int]) -> list[int]:
        d = {x: i for i, x in enumerate(order)}
        return sorted(friends, key=lambda x: d[x])
