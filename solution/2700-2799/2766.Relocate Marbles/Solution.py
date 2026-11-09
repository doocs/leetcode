class Solution:
    def relocateMarbles(
        self, nums: list[int], moveFrom: list[int], moveTo: list[int]
    ) -> list[int]:
        pos = set(nums)
        for f, t in zip(moveFrom, moveTo):
            pos.remove(f)
            pos.add(t)
        return sorted(pos)
