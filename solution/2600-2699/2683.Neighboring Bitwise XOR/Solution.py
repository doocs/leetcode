class Solution:
    def doesValidArrayExist(self, derived: list[int]) -> bool:
        return reduce(xor, derived) == 0
