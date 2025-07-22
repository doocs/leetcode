class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        def calc(k: int) -> int:
            return sum(abs(i - j) for i, j in zip(range(0, len(nums), 2), pos[k]))

        pos = [[], []]
        for i, x in enumerate(nums):
            pos[x & 1].append(i)
        if abs(len(pos[0]) - len(pos[1])) > 1:
            return -1
        if len(pos[0]) > len(pos[1]):
            return calc(0)
        if len(pos[0]) < len(pos[1]):
            return calc(1)
        return min(calc(0), calc(1))
