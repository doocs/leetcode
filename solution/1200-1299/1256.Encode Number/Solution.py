class Solution:
    def encode(self, num: int) -> str:
        return bin(num + 1)[3:]
