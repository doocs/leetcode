class Solution:
    def toggleLightBulbs(self, bulbs: list[int]) -> list[int]:
        st = [0] * 101
        for x in bulbs:
            st[x] ^= 1
        return [i for i, x in enumerate(st) if x]
